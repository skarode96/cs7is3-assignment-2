package ie.tcd.newssearch.searcher;

import ie.tcd.newssearch.indexer.IndexerCore;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class SearcherTest {

    @Test
    public void shouldRunQueries() throws IOException, ParseException {
        Directory dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
        Searcher.executeQueries(dir);
    }
}