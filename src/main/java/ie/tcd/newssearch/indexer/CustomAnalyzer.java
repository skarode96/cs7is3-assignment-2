package ie.tcd.newssearch.indexer;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.FlattenGraphFilter;
import org.apache.lucene.analysis.miscellaneous.TrimFilter;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterGraphFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.util.CharsRef;
import org.tartarus.snowball.ext.EnglishStemmer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CustomAnalyzer extends StopwordAnalyzerBase {

	private final Path currentRelativePath = Paths.get("").toAbsolutePath();

    public CustomAnalyzer(){
		super(StandardAnalyzer.ENGLISH_STOP_WORDS_SET);
	}

	@Override
	protected TokenStreamComponents createComponents(String s) {
		final Tokenizer tokenizer = new StandardTokenizer();
		TokenStream tokenStream = new StandardFilter(tokenizer);
		tokenStream = new LowerCaseFilter(tokenStream);
		tokenStream = new TrimFilter(tokenStream);
		tokenStream = new FlattenGraphFilter(new WordDelimiterGraphFilter(tokenStream, WordDelimiterGraphFilter.SPLIT_ON_NUMERICS |
							WordDelimiterGraphFilter.GENERATE_WORD_PARTS | WordDelimiterGraphFilter.GENERATE_NUMBER_PARTS |
								 WordDelimiterGraphFilter.PRESERVE_ORIGINAL , null));
		tokenStream = new FlattenGraphFilter(new SynonymGraphFilter(tokenStream, createSynonymMap(), true));
		tokenStream = new StopFilter(tokenStream, StopFilter.makeStopSet(createStopWordList(),true));
		tokenStream = new SnowballFilter(tokenStream, new EnglishStemmer());
		return new TokenStreamComponents(tokenizer, tokenStream);
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
}
