package ie.tcd.newssearch.searcher;

import ie.tcd.newssearch.builder.AnalyzerBuilder;
import ie.tcd.newssearch.builder.AnalyzerChoice;
import ie.tcd.newssearch.builder.SimilarityBuilder;
import ie.tcd.newssearch.builder.SimilarityChoice;
import ie.tcd.newssearch.topicsparser.TopicsModel;
import ie.tcd.newssearch.topicsparser.TopicsParser;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
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
    private final static String absPathToSearchResults = String.format("%s/output/", currentRelativePath);

    public static void executeQueries(Directory directory, AnalyzerChoice analyzerChoice, SimilarityChoice similarityChoice) throws ParseException {
        try {
            IndexReader indexReader = DirectoryReader.open(directory);
            Similarity similarity = SimilarityBuilder.build(similarityChoice);
            Analyzer analyzer = AnalyzerBuilder.build(analyzerChoice);

            IndexSearcher indexSearcher = createIndexSearcher(indexReader, similarity);


            Map<String, Float> boost = createBoostMap();
            QueryParser queryParser = new MultiFieldQueryParser(new String[]{"headline", "text"}, analyzer, boost);

            String outputPath = absPathToSearchResults + analyzerChoice + "-" + similarityChoice;
            PrintWriter writer = new PrintWriter(outputPath, "UTF-8");
            PrintWriter writer1 = new PrintWriter(absPathToSearchResults + "query_results.txt", "UTF-8");

            String pathToTopics = String.format("%s/dataset/topics/topics",currentRelativePath);
            List<TopicsModel> loadedQueries = TopicsParser.parse(pathToTopics);

            for (TopicsModel queryData : loadedQueries) {

                List<String> splitNarrative = splitNarrIntoRelNotRel(queryData.getTopicNarrative());
                String relevantNarr = splitNarrative.get(0).trim();
                String irrelevantNarr = splitNarrative.get(1).trim();

                BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();


                if (queryData.getTopicTitle().length() > 0) {

                    Query titleQuery = queryParser.parse(QueryParser.escape(queryData.getTopicTitle()));
                    Query descriptionQuery = queryParser.parse(QueryParser.escape(queryData.getTopicDesc()));
                    if(relevantNarr.length()>0) {
                        Query narrativeQuery = queryParser.parse(QueryParser.escape(relevantNarr));
                        if(narrativeQuery != null)
                            booleanQuery.add(new BoostQuery(narrativeQuery, 1.2f), BooleanClause.Occur.SHOULD);
                    } else if(irrelevantNarr.length() > 0) {
                        Query irrelevantNarrativeQuery = queryParser.parse(QueryParser.escape(irrelevantNarr));
                        if(irrelevantNarrativeQuery != null)
                            booleanQuery.add(new BoostQuery(irrelevantNarrativeQuery, 2f), BooleanClause.Occur.FILTER);
                    }

                    booleanQuery.add(new BoostQuery(titleQuery, 4f), BooleanClause.Occur.SHOULD);
                    booleanQuery.add(new BoostQuery(descriptionQuery, 1.7f), BooleanClause.Occur.SHOULD);

                    ScoreDoc[] hits = indexSearcher.search(booleanQuery.build(), MAX_RETURN_RESULTS).scoreDocs;

                    for (int hitIndex = 0; hitIndex < hits.length; hitIndex++) {
                        ScoreDoc hit = hits[hitIndex];
                        writer.println(queryData.getTopicNum() + ITER_NUM + indexSearcher.doc(hit.doc).get("docno") +
                                " " + hitIndex + " " + hit.score + ITER_NUM);
                        writer1.println(queryData.getTopicNum() + ITER_NUM + indexSearcher.doc(hit.doc).get("docno") +
                                " " + hitIndex + " " + hit.score + ITER_NUM);
                    }
                }
            }

            closeIndexReader(indexReader);
            closePrintWriter(writer);
            closePrintWriter(writer1);
            System.out.println("queries executed for : " + analyzerChoice + "-" + similarityChoice);
            System.out.println("queries saved to location : " + outputPath);

        } catch (IOException e) {
            System.out.println("ERROR: an error occurred when instantiating the printWriter!");
            System.out.println(String.format("ERROR MESSAGE: %s", e.getMessage()));
        }
    }

     private static Map<String, Float> createBoostMap() {
        Map<String, Float> boost = new HashMap<>();
        boost.put("headline", 0.08f);
        boost.put("text", 0.92f);
        return boost;
    }

     private static IndexSearcher createIndexSearcher(IndexReader indexReader, Similarity similarity){
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        indexSearcher.setSimilarity(similarity);
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

    private static List<String> splitNarrIntoRelNotRel(String narrative) {
        StringBuilder relevantNarr = new StringBuilder();
        StringBuilder irrelevantNarr = new StringBuilder();
        List<String> splitNarrative = new ArrayList<>();

        BreakIterator bi = BreakIterator.getSentenceInstance();
        bi.setText(narrative);
        int index = 0;
        while (bi.next() != BreakIterator.DONE) {
            String sentence = narrative.substring(index, bi.current());

            if (!sentence.contains("not relevant") && !sentence.contains("irrelevant")) {
                relevantNarr.append(sentence.replaceAll(
                        "a relevant document identifies|a relevant document could|a relevant document may|a relevant document must|a relevant document will|a document will|to be relevant|relevant documents|a document must|relevant|will contain|will discuss|will provide|must cite",
                        ""));
            } else {
                irrelevantNarr.append(sentence.replaceAll("are also not relevant|are not relevant|are irrelevant|is not relevant|not|NOT", ""));
            }
            index = bi.current();
        }
        splitNarrative.add(relevantNarr.toString());
        splitNarrative.add(irrelevantNarr.toString());
        return splitNarrative;
    }


}
