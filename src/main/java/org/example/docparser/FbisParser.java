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

public class FbisParser {
    public static List<Document> parse(String absPathTofbis) throws IOException {
        List<Document> parsedDocumentList = new ArrayList<>();

        File folder = new File(absPathTofbis);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {

            org.jsoup.nodes.Document fbisContent = Jsoup.parse(file, null, "");

            Elements docs = fbisContent.select("DOC");

            for(Element doc: docs) {
                String docNo, header, text;
                docNo = (doc.select("DOCNO").text());
                header = (doc.select("HEADER").select("F").text());
                text = (doc.select("TEXT").select("F").text());
                parsedDocumentList.add(createDocument(docNo, header, text));
            }

        }
        return parsedDocumentList;
    }

    private static Document createDocument(String docNo, String header, String text) {
        org.apache.lucene.document.Document document = new org.apache.lucene.document.Document();
        document.add(new StringField("docno", docNo, Field.Store.YES));
        document.add(new TextField("header", header, Field.Store.YES) );
        document.add(new TextField("text", text, Field.Store.YES) );
        return document;
    }

}
