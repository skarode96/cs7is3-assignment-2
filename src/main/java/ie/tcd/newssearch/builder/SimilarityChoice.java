package ie.tcd.newssearch.builder;

public enum SimilarityChoice {

    BM25("BM25"),
    Boolean("Boolean"),
    Multi("Multi"),
    Classic("Classic"),
    LMDirichlet("LMDirichlet");

    private String similarity;
    SimilarityChoice(String choice) {
        this.similarity = choice;
    }

    @Override
    public String toString(){
        return similarity;
    }
}
