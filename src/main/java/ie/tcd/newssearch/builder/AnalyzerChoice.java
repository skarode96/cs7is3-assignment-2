package ie.tcd.newssearch.builder;

public enum AnalyzerChoice {

    Simple("Simple"),
    Standard("Standard") ,
    English("English"),
    Custom("Custom");

    private String analyzerName;
    private AnalyzerChoice(String analyzerName) {
        this.analyzerName = analyzerName;
    }

    @Override
    public String toString(){
        return analyzerName;
    }
}
