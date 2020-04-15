package ie.tcd.newssearch.indexer;

public enum SimilarityChoice {

    BM25("BM25"),
    Boolean("Boolean"),
    Multi("Multi"),
    TFIDF("TFIDF"),
    Classic("Classic"),
    LMDirichlet("LMDirichlet");

    private String similarity;
    private SimilarityChoice(String choice) {
        this.similarity = choice;
    }

    @Override
    public String toString(){
        return similarity;
    }
}
