package ie.tcd.newssearch.docparser;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FR94ParserTest {
    @Test
    public void shouldParseFR94Documents() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String pathToFedRegister = String.format("%s/dataset",currentRelativePath);
        List<Document> documentList = new FR94Parser().parse(pathToFedRegister);
        Assert.assertEquals(55630, documentList.size());
    }
}
