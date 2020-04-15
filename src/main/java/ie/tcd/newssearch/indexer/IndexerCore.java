package ie.tcd.newssearch.indexer;

import ie.tcd.newssearch.docloader.DocLoader;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.ConcurrentMergeScheduler;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IndexerCore {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexerCore.class);

    public static String indexLocation = "Index/";
    public static String documentsLocation = "dataset";
    public static Analyzer analyzer;
    public static Similarity similarity;



    public static void CreateIndex(AnalyzerChoice analyzerChoice, SimilarityChoice similarityChoice) {

        LOGGER.info("Indexing started for==> " + analyzerChoice + "-" + similarityChoice);
        long start = System.currentTimeMillis();
        analyzer = AnalyzerBuilder.build(analyzerChoice);
        similarity = SimilarityBuilder.build(similarityChoice);

        try {
            indexLocation = indexLocation + analyzerChoice + "-" + similarityChoice;
            if(new File(indexLocation).exists())
                FileUtils.deleteDirectory(new File(indexLocation));
            Directory dir = FSDirectory.open(Paths.get(indexLocation));
            final IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            indexWriterConfig.setOpenMode(OpenMode.CREATE);
            indexWriterConfig.setSimilarity(similarity);
            ConcurrentMergeScheduler cms = new ConcurrentMergeScheduler();
            cms.setMaxMergesAndThreads(4, 2);
            indexWriterConfig.setMergeScheduler(cms);
            indexWriterConfig.setUseCompoundFile(false);
            IndexWriter indexWriter = new IndexWriter(dir, indexWriterConfig);

            String[] loaders = {"FBISLoader", "FR94Loader", "FTLoader", "LATimesLoader"};

            ExecutorService exe = Executors.newFixedThreadPool(4);
            int tasks = 4;
            CountDownLatch latch = new CountDownLatch(tasks);

            for (int i = 0; i < tasks; i++) {
                exe.submit(new IndexTask(loaders[i], latch));
            }
            try {
                latch.await();
                long end = System.currentTimeMillis();
                LOGGER.info("Indexing " + indexWriter.numDocs() + " documents took " + (end - start)/1000L + " seconds");
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted Indexing thread", e);
            }
            exe.shutdown();

            indexWriter.close();
            dir.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    static class IndexTask implements Runnable {
        private CountDownLatch latch;
        private String docLoaderClassName;

        public IndexTask(String loader, CountDownLatch latch) {
            this.docLoaderClassName = loader;
            this.latch = latch;
        }

        public void run() {
            try {
                DocLoader loaderInstance = (DocLoader) Class.forName("ie.tcd.newssearch.docloader." + docLoaderClassName).getConstructor().newInstance();
                List<Document> documentList = loaderInstance.load((new File(documentsLocation)).getCanonicalPath());
                // documents.addAll(documentList);
                indexWriter.addDocuments(documentList);
                LOGGER.info(docLoaderClassName + "Indexing Done");
            } catch (IOException ioe) {
                LOGGER.error("Error while parsing or indexing " + docLoaderClassName + " document", ioe);
            } catch(Exception e) {
                LOGGER.error("Error in parser class for" + docLoaderClassName, e);
            } finally {
                latch.countDown();
            }
        }
    }
}