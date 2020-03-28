package ie.tcd.newssearch.docparser;

import org.apache.lucene.document.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LATimeParser implements DocParser {

    private List<Document> parsedDocumentList = new ArrayList<>();

    @Override
    public List<Document> parse(String absPathToLaTimes) throws IOException {
        File folder = new File(absPathToLaTimes + "/latimes");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {

            org.jsoup.nodes.Document laTimesContent = Jsoup.parse(file, null, "");
            Elements docs = laTimesContent.select("DOC");

            for (Element doc : docs) {
                String docNo, headline, text;
                docNo = (doc.select("DOCNO").text());
                headline = (doc.select("HEADLINE").select("P").text());
                text = (doc.select("TEXT").select("P").text());
                parsedDocumentList.add(DocParser.createDocument(docNo, headline, text));
            }

        }
        return parsedDocumentList;
    }

}
