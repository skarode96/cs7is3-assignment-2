package ie.tcd.newssearch.docparser;

import org.apache.lucene.document.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FR94Parser implements DocParser {

    private List<Document> parsedDocumentList = new ArrayList<>();

    @Override
    public List<Document> parse(String pathToFR94) throws IOException {

        File[] directories = new File(pathToFR94 + "/fr94").listFiles(File::isDirectory);
        String docno, text, title;
        for (File directory : directories) {
            File[] files = directory.listFiles();
            for (File file : files) {
                org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(file, null, "");

                Elements documents = jsoupDoc.select("DOC");

                for (Element document : documents) {

                    title = document.select("DOCTITLE").text();
                    document.select("DOCTITLE").remove();
                    document.select("ADDRESS").remove();
                    document.select("SIGNER").remove();
                    document.select("SIGNJOB").remove();
                    document.select("BILLING").remove();
                    document.select("FRFILING").remove();
                    document.select("DATE").remove();
                    document.select("CRFNO").remove();
                    document.select("RINDOCK").remove();
                    docno = document.select("DOCNO").text();
                    text = document.select("TEXT").text();

                    parsedDocumentList.add(DocParser.createDocument(docno, title, text));
                }
            }
        }
        return parsedDocumentList;
    }
}
