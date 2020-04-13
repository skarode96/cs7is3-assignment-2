package ie.tcd.newssearch.docloader;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LATimesLoaderTest {

    @Test
    public void shouldLoadDocuments() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String pathToLaTimes = String.format("%s/dataset",currentRelativePath);
        List<Document> documentList = new LATimesLoader().load(pathToLaTimes);
        Assert.assertEquals(131896, documentList.size());
    }
}