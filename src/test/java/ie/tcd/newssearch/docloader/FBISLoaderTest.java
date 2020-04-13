package ie.tcd.newssearch.docloader;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FBISLoaderTest {

    @Test
    public void shouldLoadDocuments() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String pathToFBIDocs = String.format("%s/dataset",currentRelativePath);
        List<Document> documentList = new FBISLoader().load(pathToFBIDocs);
        Assert.assertEquals(130471, documentList.size());
    }

}