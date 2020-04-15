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

public class LaTimesParser {
	public static void main(String[] args) throws IOException {

		File baseDir = new File(Constants.PARSED_DOCUMENT_LOCATION + "latimes/");
		baseDir.mkdirs();

		List<String> results = new ArrayList<String>();
		File[] files = new File(Constants.DATASET_LOCATION + "latimes/").listFiles();
		for (File file : files) {
			try {
				if (file.isFile()) {
					System.out.println(file.getAbsolutePath());
					results.add(file.getName());
					File input = new File(file.getAbsolutePath());
					Document doc = Jsoup.parse(input, "UTF-8", "");

					doc.select("docid").remove();
					Elements docs = doc.select("doc");

					for (Element e : docs) {

						String DocNo = e.getElementsByTag("Docno").text();

						File result = new File(Constants.PARSED_DOCUMENT_LOCATION + "latimes/" + DocNo);
						PrintWriter writer = new PrintWriter(result, "UTF-8");
						writer.println(e.text());
						writer.close();

					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println(results.size());
		}
	}
}
