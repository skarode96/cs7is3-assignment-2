package ie.tcd.newssearch.docloader;

import ie.tcd.newssearch.docparser.objects.FBISObject;
import ie.tcd.newssearch.docparser.tags.FBISTags;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FBISLoader implements DocLoader {

	private static BufferedReader br;
	private static ArrayList<Document> fbisDocList;

	public ArrayList<Document> loadFBISDocs(String fbisDirectory) throws IOException {
		fbisDocList = new ArrayList<>();
		System.out.println("Loading FBIS ...");

		Directory dir = FSDirectory.open(Paths.get(fbisDirectory));
		for (String fbisFile : dir.listAll()) {
			br = new BufferedReader(new FileReader(fbisDirectory + "/" + fbisFile));
			process();
		}
		System.out.println("Loading FBIS Done!");
		return fbisDocList;
	}

	private static void process() throws IOException {

		String file = readFile();
		org.jsoup.nodes.Document document = Jsoup.parse(file);

		List<Element> list = document.getElementsByTag("doc");

		for (Element doc : list) {

			// get data from tags and start creating objects
			FBISObject fbisObject = new FBISObject();
			if (doc.getElementsByTag(FBISTags.DOCNO.getTag()) != null)
				fbisObject.setDocNo(trimData(doc, FBISTags.DOCNO));
			if (doc.getElementsByTag(FBISTags.HT.getTag()) != null)
				fbisObject.setHt(trimData(doc, FBISTags.HT));
			if (doc.getElementsByTag(FBISTags.HEADER.getTag()) != null)
				fbisObject.setHeader(trimData(doc, FBISTags.HEADER));
			if (doc.getElementsByTag(FBISTags.H2.getTag()) != null)
				fbisObject.setH2(trimData(doc, FBISTags.H2));
			if (doc.getElementsByTag(FBISTags.DATE1.getTag()) != null)
				fbisObject.setDate(trimData(doc, FBISTags.DATE1));
			if (doc.getElementsByTag(FBISTags.H3.getTag()) != null)
				fbisObject.setH3(trimData(doc, FBISTags.H3));
			if (doc.getElementsByTag(FBISTags.TI.name()) != null)
				fbisObject.setTi(trimData(doc, FBISTags.TI));
			if (doc.getElementsByTag(FBISTags.TEXT.name()) != null)
				fbisObject.setText(trimData(doc, FBISTags.TEXT));

			fbisDocList.add(createFBISDocument(fbisObject));
		}
	}

	private static String trimData(Element doc, FBISTags tag) {

		Elements element = doc.getElementsByTag(tag.name());
		// Elements tmpElement = element.clone();
		// remove any nested
		removeNestedTags(element, tag);
		String data = element.toString();

		// remove any instance of "\n"
		if (data.contains("\n"))
			data = data.replaceAll("\n", "").trim();
		// remove start and end tags
		if (data.contains(("<" + tag.name() + ">").toLowerCase()))
			data = data.replaceAll("<" + tag.name().toLowerCase() + ">", "").trim();
		if (data.contains(("</" + tag.name() + ">").toLowerCase()))
			data = data.replaceAll("</" + tag.name().toLowerCase() + ">", "").trim();

		return data;
	}

	private static void removeNestedTags(Elements element, FBISTags currTag) {

		for (FBISTags tag : FBISTags.values()) {
			if (element.toString().contains("<" + tag.name().toLowerCase() + ">")
					&& element.toString().contains("</" + tag.name().toLowerCase() + ">") && !tag.equals(currTag)) {
				element.select(tag.toString()).remove();
			}
		}
	}

	private static Document createFBISDocument(FBISObject fbisObject) {
		Document document = new Document();
		document.add(new StringField("docno", fbisObject.getDocNo(), Field.Store.YES));
		document.add(new StringField("ht", fbisObject.getHt(), Field.Store.YES));
		document.add(new StringField("h2", fbisObject.getH2(), Field.Store.YES));
		document.add(new StringField("date", fbisObject.getDate(), Field.Store.YES));
		document.add(new StringField("h3", fbisObject.getH3(), Field.Store.YES));
		document.add(new TextField("headline", fbisObject.getTi(), Field.Store.YES));
		document.add(new TextField("text", fbisObject.getText(), Field.Store.YES));
		return document;
	}

	private static String readFile() throws IOException {
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}


	@Override
	public List<Document> load(String absPathToFiles) throws IOException {
		return loadFBISDocs(absPathToFiles + "/fbis");
	}
}
