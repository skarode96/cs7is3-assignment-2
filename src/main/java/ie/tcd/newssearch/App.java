package ie.tcd.newssearch;

import ie.tcd.newssearch.indexer.AnalyzerChoice;
import ie.tcd.newssearch.indexer.IndexerCore;
import ie.tcd.newssearch.indexer.SimilarityChoice;
import ie.tcd.newssearch.searcher.Searcher;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException, ParseException {

        //BM25 with all Analyzers
        IndexerCore.indexLocation = "Index/";
        IndexerCore.CreateIndex(AnalyzerChoice.Custom, SimilarityChoice.BM25);
        Directory dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
        Searcher.executeQueries(dir, AnalyzerChoice.Custom, SimilarityChoice.BM25);

        IndexerCore.indexLocation = "Index/";
        IndexerCore.CreateIndex(AnalyzerChoice.Simple, SimilarityChoice.BM25);
        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
        Searcher.executeQueries(dir, AnalyzerChoice.Simple, SimilarityChoice.BM25);

//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.English, SimilarityChoice.BM25);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.English, SimilarityChoice.BM25);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Standard, SimilarityChoice.BM25);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Standard, SimilarityChoice.BM25);
//
//
//
//
//        //Boolean with all Analyzers
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Custom, SimilarityChoice.Boolean);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Custom, SimilarityChoice.Boolean);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Simple, SimilarityChoice.Boolean);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Simple, SimilarityChoice.Boolean);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.English, SimilarityChoice.Boolean);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.English, SimilarityChoice.Boolean);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Standard, SimilarityChoice.Boolean);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Standard, SimilarityChoice.Boolean);
//
//
//
//        //Multi with all Analyzers
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Custom, SimilarityChoice.Multi);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Custom,SimilarityChoice.Multi);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Simple, SimilarityChoice.Multi);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Simple, SimilarityChoice.Multi);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.English, SimilarityChoice.Multi);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.English, SimilarityChoice.Multi);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Standard, SimilarityChoice.Multi);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Standard, SimilarityChoice.Multi);
//
//
//
//        //Classic with all Analyzers
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Custom, SimilarityChoice.Classic);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Custom, SimilarityChoice.Classic);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Simple, SimilarityChoice.Classic);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Simple, SimilarityChoice.Classic);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.English, SimilarityChoice.Classic);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.English, SimilarityChoice.Classic);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Standard, SimilarityChoice.Classic);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Standard, SimilarityChoice.Classic);
//
//
//        //Dirichlet with all Analyzers
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Custom, SimilarityChoice.LMDirichlet);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Custom, SimilarityChoice.LMDirichlet);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Simple, SimilarityChoice.LMDirichlet);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Simple, SimilarityChoice.LMDirichlet);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.English, SimilarityChoice.LMDirichlet);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.English, SimilarityChoice.LMDirichlet);
//
//        IndexerCore.indexLocation = "Index/";
//        IndexerCore.CreateIndex(AnalyzerChoice.Standard, SimilarityChoice.LMDirichlet);
//        dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
//        Searcher.executeQueries(dir, AnalyzerChoice.Standard, SimilarityChoice.LMDirichlet);

    }
}
