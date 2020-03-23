package ie.tcd.newssearch.docparser;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FTParserTest {
    @Test
    public void shouldParseFTDocuments() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String pathToFt = String.format("%s/dataset/ft",currentRelativePath);
        List<Document> documentList = new FTParser().parse(pathToFt);
        Assert.assertEquals(210158, documentList.size());
    }
}
