package ie.tcd.newssearch.docparser;

import org.apache.lucene.document.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FbisParser implements DocParser {
    private List<Document> parsedDocumentList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(FbisParser.class);

    @Override
    public List<Document> parse(String absPathTofbis) throws IOException {

        File folder = new File(absPathTofbis + "/fbis");
        File[] listOfFiles = folder.listFiles();
        List<String> ignoreFileNameList = getIgnoreFileNameList();
        List<File> filteredFiles = Arrays.stream(listOfFiles).filter(file -> !ignoreFileNameList.contains(file.getName())).collect(Collectors.toList());
        for (File file : filteredFiles) {

            org.jsoup.nodes.Document fbisContent = Jsoup.parse(file, null, "");

            Elements docs = fbisContent.select("DOC");

            for (Element doc : docs) {
                String docNo, header, text;
                docNo = (doc.select("DOCNO").text());
                header = (doc.select("HEADER").select("F").text());
                text = (doc.select("TEXT").select("F").text());
//                LOGGER.info("Parsing Fbis Document number: " + docNo);
                parsedDocumentList.add(DocParser.createDocument(docNo, header, text));
            }

        }
        LOGGER.info("Parsing Fbis Documents Done");
        return parsedDocumentList;
    }

    private List<String> getIgnoreFileNameList() {
        return Arrays.asList("readchg.txt",  "readmefb.txt");
    }

}
