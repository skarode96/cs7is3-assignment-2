package ie.tcd.newssearch;

import ie.tcd.newssearch.indexer.IndexerCore;

public class App {
    public static void main(String[] args) {
        IndexerCore indexer = new IndexerCore();
        indexer.CreateIndex();

    }
}
