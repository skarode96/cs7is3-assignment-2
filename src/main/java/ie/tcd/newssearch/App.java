package ie.tcd.newssearch;

import ie.tcd.newssearch.indexer.IndexerCore;
import ie.tcd.newssearch.searcher.Searcher;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        IndexerCore.CreateIndex();
        Directory dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
        Searcher.executeQueries(dir);
    }
}
