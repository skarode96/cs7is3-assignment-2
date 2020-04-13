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

// Credit: https://github.com/CS7IS3-A-Y-201718-IR2/text-search-engine-ir2

public class Fr94Parser {
	public static void main(String[] args) throws IOException {

		File baseDir = new File(Constants.PARSED_DOCUMENT_LOCATION + "fr94_docs/");
		baseDir.mkdirs();

		File[] file = new File(Constants.DATASET_LOCATION + "fr94/").listFiles();
		System.out.println(file);
		ArrayList<String> files1 = new ArrayList<String>();
		for (File files : file) {
			if (files.isDirectory()) {
				System.out.println(files.getPath());
				System.out.println();
				for (File f : files.listFiles()) {
					files1.add(f.getAbsolutePath());

				}
			}
		}

		for (String f : files1) {
			try {
				System.out.println(f);
				File input = new File(f);
				Document doc = Jsoup.parse(input, "UTF-8", "");

				// Remove elements from the doc
				doc.select("docid").remove();

				Elements docs = doc.select("doc");

				for (Element e : docs) {

					String DocNo = e.getElementsByTag("Docno").text();

					File result = new File(Constants.PARSED_DOCUMENT_LOCATION + "fr94_docs/" + DocNo);
					PrintWriter writer = new PrintWriter(result, "UTF-8");
					writer.println(e.text());
					writer.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				// System.out.println("Documents parsed: " + i);
			}
		}

	}
}