package org.example.docparser;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FTParserTest {
    @Test
    public void shouldParseFR94Documents() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String pathToFedRegister = String.format("%s/dataset/ft",currentRelativePath);
        List<Document> documentList = new FTParser().parse(pathToFedRegister);
        Assert.assertEquals(210158, documentList.size());
    }
}
