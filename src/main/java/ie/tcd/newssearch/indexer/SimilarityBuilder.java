package ie.tcd.newssearch.indexer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.similarities.*;
import org.apache.lucene.search.similarities.Similarity;

public class SimilarityBuilder {
    public static Similarity build(SimilarityChoice choice) {

        switch(choice) {
            case Boolean:
                return new BooleanSimilarity();
            case BM25:
                return new BM25Similarity();
            case Multi:
                return new MultiSimilarity(new MultiSimilarity[]{getSimilarity()});
            case LMDirichlet: return new LMDirichletSimilarity();
            default: return new ClassicSimilarity();
        }
    }

    private static MultiSimilarity getSimilarity() {
        Similarity[] similarities = {
                new ClassicSimilarity(),
                new BM25Similarity(),
                new BooleanSimilarity(),
        };
        return new MultiSimilarity(similarities);
    }
}
