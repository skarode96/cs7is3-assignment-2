package ie.tcd.newssearch.docloader;

import ie.tcd.newssearch.docparser.objects.FTObject;
import ie.tcd.newssearch.docparser.tags.FTTags;
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

public class FTLoader implements DocLoader {

	private static BufferedReader br;
	private static ArrayList<Document> ftDocList;

	public ArrayList<Document> loadFTDocs(String ftDirectory) throws IOException {
		System.out.println("Loading FT ...");
		ftDocList = new ArrayList<>();
		Directory dir = FSDirectory.open(Paths.get(ftDirectory));
		for (String directory : dir.listAll()) {
			Directory insideDir = FSDirectory.open(Paths.get(ftDirectory+"/"+directory));
			for (String filename : insideDir.listAll()) {
				br = new BufferedReader(new FileReader(ftDirectory +  "/" + directory + "/" + filename));
				process();
			}
		}
		System.out.println("Loading FT Done!");
		return ftDocList;
	}

	private static void process() throws IOException {

		String file = readFile();
		org.jsoup.nodes.Document document = Jsoup.parse(file);

		List<Element> list = document.getElementsByTag("doc");

		for (Element doc : list) {

			FTObject ftObject = new FTObject();
			if (doc.getElementsByTag(FTTags.DOCNO.getTag()) != null)
				ftObject.setDocNo(trimData(doc, FTTags.DOCNO));
			if (doc.getElementsByTag(FTTags.PROFILE.getTag()) != null)
				ftObject.setProfile(trimData(doc, FTTags.PROFILE));
			if (doc.getElementsByTag(FTTags.DATE.getTag()) != null)
				ftObject.setDate(trimData(doc, FTTags.DATE));
			if (doc.getElementsByTag(FTTags.HEADLINE.getTag()) != null)
				ftObject.setHeadline(trimData(doc, FTTags.HEADLINE));
			if (doc.getElementsByTag(FTTags.PUB.name()) != null)
				ftObject.setPub(trimData(doc, FTTags.PUB));
			if (doc.getElementsByTag(FTTags.PAGE.name()) != null)
				ftObject.setPage(trimData(doc, FTTags.PAGE));
			if (doc.getElementsByTag(FTTags.BYLINE.name()) != null)
				ftObject.setByLine(trimData(doc, FTTags.BYLINE));
			if (doc.getElementsByTag(FTTags.DATELINE.name()) != null)
				ftObject.setDateLine(trimData(doc, FTTags.DATELINE));
			if (doc.getElementsByTag(FTTags.TEXT.name()) != null)
				ftObject.setText(trimData(doc, FTTags.TEXT));

			ftDocList.add(createFTDocument(ftObject));
		}
	}

	private static String trimData(Element doc, FTTags tag) {

		Elements element = doc.getElementsByTag(tag.name());
		//Elements tmpElement = element.clone();
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

	private static void removeNestedTags(Elements element, FTTags currTag) {

		for (FTTags tag : FTTags.values()) {
			if (element.toString().contains("<" + tag.name().toLowerCase() + ">")
					&& element.toString().contains("</" + tag.name().toLowerCase() + ">") && !tag.equals(currTag)) {
				element.select(tag.toString()).remove();
			}
		}
	}

	private static Document createFTDocument(FTObject ftObject) {
		Document document = new Document();
		document.add(new StringField("docno", ftObject.getDocNo(), Field.Store.YES));
		document.add(new StringField("profile", ftObject.getProfile(), Field.Store.YES));
		document.add(new StringField("date", ftObject.getDate(), Field.Store.YES));
		document.add(new TextField("headline", ftObject.getHeadline(), Field.Store.YES));
		document.add(new TextField("pub", ftObject.getPub(), Field.Store.YES));
		document.add(new TextField("page", ftObject.getPage(), Field.Store.YES));
		document.add(new TextField("byline", ftObject.getByLine(), Field.Store.YES));
		document.add(new TextField("dateline", ftObject.getDateLine(), Field.Store.YES));
		document.add(new TextField("text", ftObject.getText(), Field.Store.YES));
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
		return loadFTDocs(absPathToFiles + "/ft");
	}
}
