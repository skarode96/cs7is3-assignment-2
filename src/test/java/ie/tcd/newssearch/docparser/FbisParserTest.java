package ie.tcd.newssearch.docparser;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FbisParserTest {
    @Test
    public void shouldParseFbisDocs() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String absPathTofbis = String.format("dataset",currentRelativePath);
        List<Document> documentList = new FbisParser().parse(absPathTofbis);
        Assert.assertEquals(130474, documentList.size());
    }
}
