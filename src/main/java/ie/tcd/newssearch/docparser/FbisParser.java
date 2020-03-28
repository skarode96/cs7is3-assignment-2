package ie.tcd.newssearch.docparser;

import org.apache.lucene.document.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FbisParser implements DocParser {
    private List<Document> parsedDocumentList = new ArrayList<>();

    @Override
    public List<Document> parse(String absPathTofbis) throws IOException {

        File folder = new File(absPathTofbis);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {

            org.jsoup.nodes.Document fbisContent = Jsoup.parse(file, null, "");

            Elements docs = fbisContent.select("DOC");

            for (Element doc : docs) {
                String docNo, header, text;
                docNo = (doc.select("DOCNO").text());
                header = (doc.select("HEADER").select("F").text());
                text = (doc.select("TEXT").select("F").text());
                parsedDocumentList.add(DocParser.createDocument(docNo, header, text));
            }

        }
        return parsedDocumentList;
    }

}
