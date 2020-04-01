package ie.tcd.newssearch.searcher;

import ie.tcd.newssearch.indexer.IndexerCore;
import ie.tcd.newssearch.topicsparser.TopicsModel;
import ie.tcd.newssearch.topicsparser.TopicsParser;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Searcher {

    private static final int MAX_RETURN_RESULTS = 1000;
    private static final String ITER_NUM = " 0 ";

    private final static Path currentRelativePath = Paths.get("").toAbsolutePath();
    private final static String absPathToSearchResults = String.format("%s/output/query_results", currentRelativePath);

    public static void executeQueries(Directory directory) throws ParseException {
        try {
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = createIndexSearcher(indexReader, IndexerCore.getSimilarity());

            Map<String, Float> boost = createBoostMap();
            QueryParser queryParser = new MultiFieldQueryParser(new String[]{"headline", "text"}, IndexerCore.getAnalyzer(), boost);

            PrintWriter writer = new PrintWriter(absPathToSearchResults, "UTF-8");

            String pathToTopics = String.format("%s/dataset/topics/topics",currentRelativePath);
            List<TopicsModel> loadedQueries = TopicsParser.parse(pathToTopics);

            for (TopicsModel queryData : loadedQueries) {

                BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();

                if (queryData.getTopicTitle().length() > 0) {

                    Query titleQuery = queryParser.parse(QueryParser.escape(queryData.getTopicTitle()));
                    Query descriptionQuery = queryParser.parse(QueryParser.escape(queryData.getTopicDesc()));
                    Query narrativeQuery = queryParser.parse(QueryParser.escape(queryData.getTopicNarrative()));


                    booleanQuery.add(new BoostQuery(titleQuery, (float) 4), BooleanClause.Occur.SHOULD);
                    booleanQuery.add(new BoostQuery(descriptionQuery, (float) 1.7), BooleanClause.Occur.SHOULD);

                    if (narrativeQuery != null) {
                        booleanQuery.add(new BoostQuery(narrativeQuery, (float) 1.2), BooleanClause.Occur.SHOULD);
                    }
                    ScoreDoc[] hits = indexSearcher.search(booleanQuery.build(), MAX_RETURN_RESULTS).scoreDocs;

                    for (int hitIndex = 0; hitIndex < hits.length; hitIndex++) {
                        ScoreDoc hit = hits[hitIndex];
                        writer.println(queryData.getTopicNum() + ITER_NUM + indexSearcher.doc(hit.doc).get("docno") +
                                " " + hitIndex + " " + hit.score + ITER_NUM);
                    }
                }
            }

            closeIndexReader(indexReader);
            closePrintWriter(writer);
            System.out.println("queries executed");

        } catch (IOException e) {
            System.out.println("ERROR: an error occurred when instantiating the printWriter!");
            System.out.println(String.format("ERROR MESSAGE: %s", e.getMessage()));
        }
    }

     private static Map<String, Float> createBoostMap() {
        Map<String, Float> boost = new HashMap<>();
        boost.put("headline", (float) 0.1);
        boost.put("text", (float) 0.9);
        return boost;
    }

     private static IndexSearcher createIndexSearcher(IndexReader indexReader, Similarity similarityModel){
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        indexSearcher.setSimilarity(similarityModel);
        return indexSearcher;
    }

    private static void closePrintWriter(PrintWriter writer){
        writer.flush();
        writer.close();
    }

    private static void closeIndexReader(IndexReader indexReader) {
        try {
            indexReader.close();
        } catch (IOException e) {
            System.out.println("ERROR: an error occurred when closing the index from the directory!");
            System.out.println(String.format("ERROR MESSAGE: %s", e.getMessage()));
        }
    }


}
