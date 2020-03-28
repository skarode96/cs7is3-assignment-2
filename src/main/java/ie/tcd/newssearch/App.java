package ie.tcd.newssearch;

import ie.tcd.newssearch.indexer.IndexerCore;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        IndexerCore indexer = new IndexerCore();
        indexer.CreateIndex();
        
    }
}
