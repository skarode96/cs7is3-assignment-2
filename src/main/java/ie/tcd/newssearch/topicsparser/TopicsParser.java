package ie.tcd.newssearch.topicsparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopicsParser {

    private static List<TopicsModel> parsedTopicsList = new ArrayList<>();
    private final static String COLON      = ":";
    private final static String WHITESPACE = " ";
    private final static String LINEFEED   = "\\n";
    private final static String NEWLINE    = "\\n\\n";

    public static List<TopicsModel> parse(String absPathToTopicsFile) throws IOException {
        String num, title, desc, narr;
        org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(new File(absPathToTopicsFile), "UTF-8", "");

        jsoupDoc.outputSettings(new Document.OutputSettings().prettyPrint(false));

        Elements topics = jsoupDoc.select(TopicTags.TOPIC_TAG.getTopicTag());

        for (Element topic : topics) {
            num = topic.select(TopicTags.TOPIC_NUM.getTopicTag()).text().split(WHITESPACE, 3)[1].trim();
            title = topic.select(TopicTags.TOPIC_TITLE.getTopicTag()).text().trim();
            desc = extractWhenNoClosingTag(topic.select(TopicTags.TOPIC_DESCRIPTION.getTopicTag()).html());
            narr = extractWhenNoClosingTag(topic.select(TopicTags.TOPIC_NARRATIVE.getTopicTag()).html());

            parsedTopicsList.add(new TopicsModel(num, title, desc, narr));
        }

        return parsedTopicsList;
    }

    private static String extractWhenNoClosingTag(String html) {
        return html.split(NEWLINE,2)[0].split(COLON, 2)[1].replaceAll(LINEFEED, WHITESPACE).trim();
    }

}
