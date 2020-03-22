package org.example.docparser;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LATimeParser {
    public static List<Document> parse(String absPathToLaTimes) throws IOException {
        List<Document> parsedDocumentList = new ArrayList<>();

        File folder = new File(absPathToLaTimes);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {

            org.jsoup.nodes.Document laTimesContent = Jsoup.parse(file, null, "");

            Elements docs = laTimesContent.select("DOC");

            for(Element doc: docs) {
                String docNo, headline, text;
                docNo = (doc.select("DOCNO").text());
                headline = (doc.select("HEADLINE").select("P").text());
                text = (doc.select("TEXT").select("P").text());
                parsedDocumentList.add(createDocument(docNo, headline, text));
            }

        }
        return parsedDocumentList;
    }

    private static Document createDocument(String docNo, String headline,String text) {
        org.apache.lucene.document.Document document = new org.apache.lucene.document.Document();
        document.add(new StringField("docno", docNo, Field.Store.YES));
        document.add(new TextField("headline", headline, Field.Store.YES) );
        document.add(new TextField("text", text, Field.Store.YES) );
        return document;
    }

}
