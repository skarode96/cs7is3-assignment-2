package ie.tcd.newssearch;

import ie.tcd.newssearch.builder.AnalyzerChoice;
import ie.tcd.newssearch.indexer.IndexerCore;
import ie.tcd.newssearch.builder.SimilarityChoice;
import org.junit.Ignore;
import org.junit.Test;

public class AppTest 
{
    @Test
    @Ignore
    public void shouldIndex()
    {
        IndexerCore.CreateIndex(AnalyzerChoice.Custom, SimilarityChoice.BM25);
    }

}
