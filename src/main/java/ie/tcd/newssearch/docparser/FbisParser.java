package ie.tcd.newssearch.docparser;

import ie.tcd.newssearch.constants.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FbisParser {
	public static void main(String[] args) throws IOException {

		List<String> results = new ArrayList<String>();
		File[] file = new File(Constants.DATASET_LOCATION + "fbis/").listFiles();

		File baseDir = new File(Constants.PARSED_DOCUMENT_LOCATION + "fbis_docs/");
		baseDir.mkdirs();
		int i = 0;
		try {

			for (File files : file) {
				try {
					if (files.isFile()) {
						System.out.println(files.getAbsolutePath());
						results.add(files.getName());
						File input = new File(files.getAbsolutePath());
						Document doc = Jsoup.parse(input, "UTF-8", "");

						doc.select("docid").remove();

						Elements docs = doc.select("doc");

						for (Element e : docs) {
							i++;
							String DocNo = e.getElementsByTag("Docno").text();
							File result = new File(Constants.PARSED_DOCUMENT_LOCATION + "fbis_docs/" + DocNo);

							String entireDoc = e.getAllElements().text();

							PrintWriter writer = new PrintWriter(result, "UTF-8");
							writer.println(entireDoc);
							writer.close();
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Documents parsed: " + i);
		}

		System.out.println(results.size());
	}
}
