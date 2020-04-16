package ie.tcd.newssearch.indexer;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.FlattenGraphFilter;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.miscellaneous.TrimFilter;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterGraphFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.util.CharsRef;
import org.tartarus.snowball.ext.EnglishStemmer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Customized {@link Analyzer} based on EnglishAnalyzer.
 */
public class NewsAnalyzer extends StopwordAnalyzerBase {

    /**
     * An unmodifiable set containing some common English words that are not usually
     * useful for searching.
     */
    public static final CharArraySet STOP_WORDS_SET;
    private final Path currentRelativePath = Paths.get("").toAbsolutePath();


    static {
        // This is an extended stop words set derived from NLTK source
        final List<String> stopWords = Arrays.asList("i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "you're",
                "you've", "you'll", "you'd", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself",
                "she", "she's", "her", "hers", "herself", "it", "it's", "its", "itself", "they", "them", "their",
                "theirs", "themselves", "what", "which", "who", "whom", "this", "that", "that'll", "these", "those",
                "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having", "do", "does",
                "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while", "of",
                "at", "by", "for", "with", "about", "between", "into", "through", "during", "before", "after", "above",
                "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again", "further",
                "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each", "few",
                "more", "most", "other", "some", "such", "only", "own", "same", "so", "than", "too", "very", "s", "t",
                "can", "will", "just", "don", "don't", "should", "should've", "now", "d", "ll", "m", "re", "ve");
        final CharArraySet stopSet = new CharArraySet(stopWords, false);
        STOP_WORDS_SET = CharArraySet.unmodifiableSet(stopSet);
    }

    private final CharArraySet stemExclusionSet;

    /**
     * Builds an analyzer with the default stop words: {@link #getDefaultStopSet}.
     */
    public NewsAnalyzer() {
        this(STOP_WORDS_SET);
    }

    /**
     * Builds an analyzer with the given stop words.
     *
     * @param stopwords a stopword set
     */
    public NewsAnalyzer(CharArraySet stopwords) {
        this(stopwords, CharArraySet.EMPTY_SET);
    }

    /**
     * Builds an analyzer with the given stop words. If a non-empty stem exclusion set is
     * provided this analyzer will add a {@link SetKeywordMarkerFilter} before
     * stemming.
     *
     * @param stopwords        a stopword set
     * @param stemExclusionSet a set of terms not to be stemmed
     */
    public NewsAnalyzer(CharArraySet stopwords, CharArraySet stemExclusionSet) {
        super(stopwords);
        this.stemExclusionSet = CharArraySet.unmodifiableSet(CharArraySet.copy(stemExclusionSet));
    }

    /**
     * Returns an unmodifiable instance of the default stop words set.
     *
     * @return default stop words set.
     */
    public static CharArraySet getDefaultStopSet() {
        return STOP_WORDS_SET;
    }

    /**
     * Creates a {@link org.apache.lucene.analysis.Analyzer.TokenStreamComponents}
     * which tokenizes all the text in the provided {@link Reader}.
     *
     * @return A {@link org.apache.lucene.analysis.Analyzer.TokenStreamComponents}
     * built from an {@link StandardTokenizer} filtered with
     * {@link EnglishPossessiveFilter}, {@link LowerCaseFilter},
     * {@link StopFilter} , {@link SetKeywordMarkerFilter} if a stem
     * exclusion set is provided and {@link PorterStemFilter}.
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        // TODO (Nikhil) - Add a stemmer here.
        final Tokenizer source = new StandardTokenizer();
        TokenStream result = new EnglishPossessiveFilter(source);
        result = new LowerCaseFilter(result);
        result = new TrimFilter(result);
        result = new FlattenGraphFilter(new WordDelimiterGraphFilter(result, WordDelimiterGraphFilter.SPLIT_ON_NUMERICS |
                WordDelimiterGraphFilter.GENERATE_WORD_PARTS | WordDelimiterGraphFilter.GENERATE_NUMBER_PARTS |
                WordDelimiterGraphFilter.PRESERVE_ORIGINAL , null));
        result = new FlattenGraphFilter(new SynonymGraphFilter(result, createSynonymMap(), true));

//        result = new StopFilter(result, stopwords);
        if (!stemExclusionSet.isEmpty())
            result = new SetKeywordMarkerFilter(result, stemExclusionSet);

        result = new StopFilter(result, StopFilter.makeStopSet(createStopWordList(),true));
        result = new SnowballFilter(result, new EnglishStemmer());
        return new TokenStreamComponents(source, result);
    }

    @Override
    protected TokenStream normalize(String fieldName, TokenStream in) {
        return new LowerCaseFilter(in);
    }

    private List<String> createStopWordList()
    {
        ArrayList<String> stopWordList = new ArrayList<>();
        try {
            BufferedReader stopwords = new BufferedReader(new FileReader(currentRelativePath + "/dataset/stopwords.txt"));
            String word = stopwords.readLine();
            while(word != null) {
                stopWordList.add(word);
                word = stopwords.readLine();
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getLocalizedMessage() + "occurred when trying to create stopword list");
        }
        return stopWordList;
    }

    private SynonymMap createSynonymMap() {
        SynonymMap synMap = new SynonymMap(null, null, 0);
        try {
            BufferedReader countries = new BufferedReader(new FileReader(currentRelativePath + "/dataset/countries.txt"));

            final SynonymMap.Builder builder = new SynonymMap.Builder(true);
            String country = countries.readLine();

            while(country != null) {
                builder.add(new CharsRef("country"), new CharsRef(country), true);
                builder.add(new CharsRef("countries"), new CharsRef(country), true);
                country = countries.readLine();
            }

            synMap = builder.build();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getLocalizedMessage() + "occurred when trying to create synonym map");
        }
        return synMap;
    }
}

