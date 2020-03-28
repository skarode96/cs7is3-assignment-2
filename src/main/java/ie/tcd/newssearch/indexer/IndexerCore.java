package ie.tcd.newssearch.indexer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.MultiSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import ie.tcd.newssearch.docparser.FR94Parser;
import ie.tcd.newssearch.docparser.FTParser;
import ie.tcd.newssearch.docparser.FbisParser;
import ie.tcd.newssearch.docparser.LATimeParser;

public class IndexerCore {

	public String indexLocation;
	public String documentsLocation;

	public IndexerCore(String documentsDirectory, String indexDirectory) {
		this.indexLocation = indexDirectory;
		this.documentsLocation = documentsDirectory;
	}

	public IndexerCore() {
		this.indexLocation = "dataset";
		this.documentsLocation = "index";
	}

	public void CreateIndex() {

		System.out.println("Indexing Begin...");
		final Analyzer azer = getAnalyzer();
		try {
			Directory dir = FSDirectory.open(Paths.get(this.indexLocation));
			final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(azer);
			indexWriterConfig.setOpenMode(OpenMode.CREATE);

			indexWriterConfig.setSimilarity(getSimilarity());

			final IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);

			// ArrayList of documents in the corpus
			ArrayList<Document> documents = new ArrayList<Document>();

			documents.addAll(FbisParser.parse((new File(documentsLocation + "/fbis")).getCanonicalPath()));
			documents.addAll(new FTParser().parse((new File(documentsLocation + "/ft")).getCanonicalPath()));
			documents.addAll(FR94Parser.parse((new File(documentsLocation + "/fr94")).getCanonicalPath()));
			documents.addAll(LATimeParser.parse((new File(documentsLocation + "/latimes")).getCanonicalPath()));

			indexWriter.addDocuments(documents);
			indexWriter.close();
			dir.close();
			System.out.println("Indexed.");
		} catch (Exception ex) {
			System.out.println(ex.toString());
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

	}

	public MultiSimilarity getSimilarity() {

		Similarity[] similarities = { new ClassicSimilarity()
				// , new BM25Similarity()
				// , new AxiomaticF1LOG()
				// , new BooleanSimilarity(),
				// , new LMJelinekMercerSimilarity(0.2f)
				// , new LMDirichletSimilarity()
		};
		return new MultiSimilarity(similarities);
	}

	public Analyzer getAnalyzer() {
		// CustomAnalyzer.builder().withTokenizer(StandardTokenizerFactory.class).addTokenFilter(EnglishPossessiveFilterFactory.class,
		// params)
		// .build();
//        EnglishAnalyzer analyzer = new EnglishAnalyzer();
//		StandardAnalyzer analyzer = new StandardAnalyzer();
		StandardAnalyzer analyzer = new StandardAnalyzer(EnglishAnalyzer.getDefaultStopSet());
		return analyzer;
	}
}
