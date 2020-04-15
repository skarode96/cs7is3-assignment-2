package ie.tcd.newssearch.builder;

import ie.tcd.newssearch.indexer.CustomAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class AnalyzerBuilder {
    public static Analyzer build(AnalyzerChoice choice) {
        switch(choice) {
            case Simple:
                return new SimpleAnalyzer();

            case English:
                return new EnglishAnalyzer();

            case Custom:
                return new CustomAnalyzer();

            default:
                return new StandardAnalyzer();
        }
    }
}
