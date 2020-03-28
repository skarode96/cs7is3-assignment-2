package ie.tcd.newssearch.indexer;

import ie.tcd.newssearch.docparser.DocParser;
import org.apache.lucene.analysis.Analyzer;
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

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IndexerCore {

    public String indexLocation;
    public String documentsLocation;

    // List of documents in the corpus
    // public List<Document> documents = Collections.synchronizedList(new ArrayList<Document>());

    private IndexWriter indexWriter;

    public IndexerCore(String documentsDirectory, String indexDirectory) {
        this.indexLocation = indexDirectory;
        this.documentsLocation = documentsDirectory;
    }

    public IndexerCore() {
        this.indexLocation = "index";
        this.documentsLocation = "dataset";
    }

    public void CreateIndex() {

        System.out.println("Indexing Begin...");
        long start = System.currentTimeMillis();
        final Analyzer azer = getAnalyzer();

        try {
            Directory dir = FSDirectory.open(Paths.get(this.indexLocation));

            final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(azer);
            indexWriterConfig.setOpenMode(OpenMode.CREATE);
            indexWriterConfig.setSimilarity(getSimilarity());
            indexWriter = new IndexWriter(dir, indexWriterConfig);

            String[] parsers = {"FTParser", "FbisParser", "FR94Parser", "LATimeParser"};

            ExecutorService exe = Executors.newFixedThreadPool(4);
            int tasks = 4;
            CountDownLatch latch = new CountDownLatch(tasks);

            for (int i = 0; i < tasks; i++) {
                exe.submit(this.new IndexTask(parsers[i], latch));
            }
            try {
                latch.await();
                System.out.println("Summary:");
                long end = System.currentTimeMillis();
                System.out.println("Indexing " + indexWriter.numDocs() + " documents took " + (end - start)/1000L + " seconds");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            exe.shutdown();

            indexWriter.close();
            dir.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public MultiSimilarity getSimilarity() {

        Similarity[] similarities = {new ClassicSimilarity()
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

    class IndexTask implements Runnable {
        private CountDownLatch latch;
        private String parser;

        public IndexTask(String parser, CountDownLatch latch) {
            this.parser = parser;
            this.latch = latch;
        }

        public void run() {
            try {
                DocParser parserInstance = (DocParser) Class.forName("ie.tcd.newssearch.docparser." + parser).getConstructor().newInstance();
                List<Document> documentList = parserInstance.parse((new File(documentsLocation)).getCanonicalPath());
                // documents.addAll(documentList);
                indexWriter.addDocuments(documentList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            latch.countDown();
        }
    }
}