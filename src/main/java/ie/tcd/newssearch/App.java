package ie.tcd.newssearch;

import ie.tcd.newssearch.builder.AnalyzerChoice;
import ie.tcd.newssearch.indexer.IndexerCore;
import ie.tcd.newssearch.builder.SimilarityChoice;
import ie.tcd.newssearch.searcher.Searcher;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException, ParseException {

        AnalyzerChoice analyzerChoice;
        SimilarityChoice similarityChoice;

        System.out.flush();
        System.out.println(" ");
        System.out.print("===CS7IS3 Group Assignment-2 Group-7===");
        System.out.println("Choose Analyzer");
        System.out.println("1 - Custom");
        System.out.println("2 - Standard");
        System.out.println("3 - English");
        System.out.println("4 - Simple");
        System.out.println("5 - Whitespace");
        System.out.println("6 - Stop");
        System.out.println("7 - News");

        String input = System.console().readLine();

        switch (input) {
            case "1":
                analyzerChoice = AnalyzerChoice.Custom;
                break;
            case "2":
                analyzerChoice = AnalyzerChoice.Standard;
                break;
            case "3":
                analyzerChoice = AnalyzerChoice.English;
                break;
            case "4":
                analyzerChoice = AnalyzerChoice.Simple;
                break;
            case "5":
                analyzerChoice = AnalyzerChoice.Whitespace;
                break;
            case "6":
                analyzerChoice = AnalyzerChoice.Stop;
                break;
            case "7":
                analyzerChoice = AnalyzerChoice.News;
                break;
            default:
                analyzerChoice = AnalyzerChoice.Standard;
                break;
        }

        System.out.println("Choose Similarity");
        System.out.println("1 - BM25Similarity");
        System.out.println("2 - MultiSimilarity");
        System.out.println("3 - LMDirichlet");
        System.out.println("4 - Boolean");
        System.out.println("5 - Classic");

        input = System.console().readLine();

        switch (input) {
            case "1":
                similarityChoice = SimilarityChoice.BM25;
                break;
            case "2":
                similarityChoice = SimilarityChoice.Multi;
                break;
            case "3":
                similarityChoice = SimilarityChoice.LMDirichlet;
                break;
            case "4":
                similarityChoice = SimilarityChoice.Boolean;
                break;
            case "5":
                similarityChoice = SimilarityChoice.Classic;
                break;
            default:
                similarityChoice = SimilarityChoice.Classic;
                break;
        }
        IndexerCore.indexLocation = "Index/";
        IndexerCore.CreateIndex(analyzerChoice, similarityChoice);
        Directory dir = FSDirectory.open(Paths.get(IndexerCore.indexLocation));
        Searcher.executeQueries(dir, analyzerChoice, similarityChoice);

    }
}
