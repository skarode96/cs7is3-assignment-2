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

public class FbisParser implements DocParser {
    private List<Document> parsedDocumentList = new ArrayList<>();
    //private static final Logger LOGGER = LoggerFactory.getLogger(FbisParser.class);

    @Override
    public List<Document> parse(String absPathTofbis) throws IOException {

        File folder = new File(absPathTofbis + "/fbis");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {

            org.jsoup.nodes.Document fbisContent = Jsoup.parse(file, null, "");

            Elements docs = fbisContent.select("DOC");

            for (Element doc : docs) {
                String docNo, header, text;
                docNo = (doc.select("DOCNO").text());
                header = (doc.select("HEADER").select("F").text());
                text = (doc.select("TEXT").select("F").text());
                //LOGGER.debug("Parsing Fbis Document number: " + docNo);
                parsedDocumentList.add(DocParser.createDocument(docNo, header, text));
            }

        }
        return parsedDocumentList;
    }

}
