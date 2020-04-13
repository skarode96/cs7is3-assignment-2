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

public class FtParser {
	public static void main(String[] args) throws IOException {

		File baseDir = new File(Constants.PARSED_DOCUMENT_LOCATION + "ft_docs/");
		baseDir.mkdirs();

		File[] file = new File(Constants.DATASET_LOCATION + "ft/").listFiles();
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

				doc.select("docid").remove();
				// doc.select("tablerow").remove();
				// doc.select("table").remove();
				// doc.select("tablecell").remove();
				// doc.select("rowrule").remove();
				// doc.select("cellrule").remove();
				// doc.select("section").remove();
				// doc.select("length").remove();
				// doc.select("graphic").remove();
				// doc.select("docid").remove();
				// doc.select("dateline").remove();
				// doc.select("date").remove();
				// doc.select("correction-date").remove();

				Elements docs = doc.select("doc");
				// System.out.println(docs.size());

				for (Element e : docs) {

					String DocNo = e.getElementsByTag("Docno").text();
					// String TextContent = e.getElementsByTag("Text").text();
					// String Headline = e.getElementsByTag("Headline").text();
					// String ByLine = e.getElementsByTag("Byline").text();
					// String Profile =e.getElementsByTag("Profile").text();

					//
					// System.out.println(DocNo + ": ");
					// System.out.println("Headline" + ": " + Headline);
					// System.out.println("TextContent" + ": " + TextContent);
					// System.out.println("ByLine" + ": " + ByLine);
					// System.out.println("Profile" + ": " + Profile);

					File result = new File(Constants.PARSED_DOCUMENT_LOCATION + "ft_docs/" + DocNo);
					PrintWriter writer = new PrintWriter(result, "UTF-8");
					// writer.println(DocNo + '\n'+ Headline + '\n'+ TextContent+'\n'+ ByLine +
					// '\n'+ Profile);
					writer.println(e.text());
					writer.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}