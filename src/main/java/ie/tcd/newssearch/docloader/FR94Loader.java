//Reference:- https://github.com/kerinb/IR_proj2_group14/tree/master/src/com/kerinb/IR_proj2_group14

package ie.tcd.newssearch.docloader;

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

public class FR94Loader implements DocLoader {
	private static ArrayList<Document> fedRegisterDocList = new ArrayList<>();

    public ArrayList<Document> loadFR94Docs(String pathToFedRegister) throws IOException {
        System.out.println("Loading FR94 ...");
        File[] directories = new File(pathToFedRegister).listFiles(File::isDirectory);
        String docno,text,title;
        for (File directory : directories) {
            File[] files = directory.listFiles();
            for (File file : files) {
                org.jsoup.nodes.Document d = Jsoup.parse(file, null, "");

                Elements documents = d.select("DOC");

                for (Element document : documents) {

                    document.select("ADDRESS").remove();
                    document.select("SIGNER").remove();
                    document.select("SIGNJOB").remove();
                    document.select("BILLING").remove();
                    document.select("FRFILING").remove();
                    document.select("DATE").remove();
                    document.select("RINDOCK").remove();

                    docno = document.select("DOCNO").text();
                    text = document.select("TEXT").text();
                    title = document.select("DOCTITLE").text();

                    addFedRegisterDoc(docno, text, title);
                }
            }
        }
        System.out.println("Loading FR94 Done!");
        return fedRegisterDocList;
    }

    private static void addFedRegisterDoc(String docno, String text, String title) {
        Document doc = new Document();
        doc.add(new StringField("docno", docno, Field.Store.YES));
        doc.add(new TextField("text", text, Field.Store.YES));
        doc.add(new TextField("headline", title, Field.Store.YES));
        fedRegisterDocList.add(doc);
    }

    @Override
    public List<Document> load(String absPathToFiles) throws IOException {
        return loadFR94Docs(absPathToFiles + "/fr94");
    }
}
