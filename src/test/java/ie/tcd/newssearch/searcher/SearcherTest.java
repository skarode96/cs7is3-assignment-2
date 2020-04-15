package ie.tcd.newssearch.searcher;

import ie.tcd.newssearch.indexer.AnalyzerChoice;
import ie.tcd.newssearch.indexer.IndexerCore;
import ie.tcd.newssearch.indexer.SimilarityChoice;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class SearcherTest {

    @Test
    public void shouldRunQueries() throws IOException, ParseException {
        AnalyzerChoice analyzerChoice = AnalyzerChoice.Custom;
        SimilarityChoice similarityChoice = SimilarityChoice.BM25;
        Directory dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation + analyzerChoice + "-" + similarityChoice));
        Searcher.executeQueries(dir, analyzerChoice, similarityChoice);
    }
}