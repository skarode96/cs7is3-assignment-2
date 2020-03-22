package org.example.docparser;

import org.apache.lucene.document.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FTParser implements DocParser {

    private List<Document> parsedDocumentList = new ArrayList<>();

    @Override
    public List<Document> parse(String pathToFT) throws IOException {
        File[] directories = new File(pathToFT).listFiles(File::isDirectory);
        String docNo,text,header;

        assert directories != null;
        for (File directory : directories) {
            File[] files = directory.listFiles();

            assert files != null;
            for (File file : files) {
                org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(file, null, "");
                Elements documents = jsoupDoc.select("DOC");

                for (Element document : documents) {
                    docNo = document.select("DOCNO").text();
                    header = document.select("HEADLINE").text();
                    text = document.select("TEXT").text();
                    parsedDocumentList.add(DocParser.createDocument(docNo, header, text));
                }
            }
        }
        return parsedDocumentList;
    }
}
