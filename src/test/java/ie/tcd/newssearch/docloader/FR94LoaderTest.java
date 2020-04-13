package ie.tcd.newssearch.docloader;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FR94LoaderTest {

    @Test
    public void shouldLoadDocuments() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String pathToFr94docs = String.format("%s/dataset",currentRelativePath);
        List<Document> documentList = new FR94Loader().load(pathToFr94docs);
        Assert.assertEquals(55630, documentList.size());
    }
}