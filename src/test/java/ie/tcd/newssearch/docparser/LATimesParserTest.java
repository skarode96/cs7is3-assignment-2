package ie.tcd.newssearch.docparser;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LATimesParserTest {

    @Test
    public void shouldParseLATimesDocs() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String absPathToLaTimes = String.format("%s/dataset",currentRelativePath);
        List<Document> documentList = new LATimeParser().parse(absPathToLaTimes);
        Assert.assertEquals(131896, documentList.size());
    }
}
