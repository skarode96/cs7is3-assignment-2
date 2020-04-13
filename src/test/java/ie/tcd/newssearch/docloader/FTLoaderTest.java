package ie.tcd.newssearch.docloader;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FTLoaderTest {

    @Test
    public void shouldLoadDocuments() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String pathToFTDocs = String.format("%s/dataset",currentRelativePath);
        List<Document> documentList = new FTLoader().load(pathToFTDocs);
        Assert.assertEquals(210158, documentList.size());
    }
}