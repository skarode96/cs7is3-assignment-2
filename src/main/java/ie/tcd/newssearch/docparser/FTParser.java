package ie.tcd.newssearch.docparser;

import org.apache.lucene.document.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FTParser implements DocParser {

    private List<Document> parsedDocumentList = new ArrayList<>();
    //private static final Logger LOGGER = LoggerFactory.getLogger(FTParser.class);

    @Override
    public List<Document> parse(String absPathToFT) throws IOException {
        File[] directories = new File(absPathToFT + "/ft").listFiles(File::isDirectory);
        String docNo,text,header;

        assert directories != null;
        for (File directory : directories) {
            File[] files = directory.listFiles();

            assert files != null;
            for (File file : files) {
                org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(file, "UTF-8", "");
                Elements documents = jsoupDoc.select("DOC");

                for (Element document : documents) {
                    docNo = document.select("DOCNO").text();
                    header = document.select("HEADLINE").text();
                    text = document.select("TEXT").text();
                    //LOGGER.debug("Parsed FT Document number: " + docNo);
                    parsedDocumentList.add(DocParser.createDocument(docNo, header, text));
                }
            }
        }
        return parsedDocumentList;
    }
}
