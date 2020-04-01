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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LATimeParser implements DocParser {

    private List<Document> parsedDocumentList = new ArrayList<>();
    //private static final Logger LOGGER = LoggerFactory.getLogger(LATimeParser.class);

    @Override
    public List<Document> parse(String absPathToLaTimes) throws IOException {
        File folder = new File(absPathToLaTimes + "/latimes");
        File[] listOfFiles = folder.listFiles();
        List<String> ignoreFileNameList = getIgnoreFileNameList();
        List<File> filteredFiles = Arrays.stream(listOfFiles).filter(file -> !ignoreFileNameList.contains(file.getName())).collect(Collectors.toList());
        for (File file : filteredFiles) {

            org.jsoup.nodes.Document laTimesContent = Jsoup.parse(file, null, "");
            Elements docs = laTimesContent.select("DOC");

            for (Element doc : docs) {
                String docNo, headline, text;
                docNo = (doc.select("DOCNO").text());
                headline = (doc.select("HEADLINE").select("P").text());
                text = (doc.select("TEXT").select("P").text());
                //LOGGER.debug("Parsed LATime Document number: " + docNo);
                parsedDocumentList.add(DocParser.createDocument(docNo, headline, text));
            }

        }
        return parsedDocumentList;
    }

    private List<String> getIgnoreFileNameList() {
        return Arrays.asList("readchg.txt",  "readmela.txt");
    }

}
