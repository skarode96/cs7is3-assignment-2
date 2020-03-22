package org.example.docparser;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class fbisParserTest {
    @Test
    public void shouldParsefbisDocs() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String absPathTofbis = String.format("dataset/fbis",currentRelativePath);
        List<Document> documentList = fbisParser.parse(absPathTofbis);
        Assert.assertEquals(130474, documentList.size());
    }
}
