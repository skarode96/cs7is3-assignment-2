package ie.tcd.newssearch.topicsparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopicsParser {

//    private final static String COLON      = ":";
//    private final static String WHITESPACE = " ";
//    private final static String LINEFEED   = "\\n";
//    private final static String NEWLINE    = "\\n\\n";
    private static List<TopicsModel> parsedTopicsList = new ArrayList<>();

    public static List<TopicsModel> parse(String absPathToTopicsFile) throws IOException {

        org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(new File(absPathToTopicsFile), "UTF-8", "");

        jsoupDoc.outputSettings(new Document.OutputSettings().prettyPrint(false));

        Elements topics = jsoupDoc.select(TopicTags.TOPIC_TAG.getTopicTag());

        for (Element topicElement : topics) {
            String num = topicElement.getElementsByTag(TopicTags.TOPIC_NUM.getTopicTag()).text();
            String title = topicElement.getElementsByTag(TopicTags.TOPIC_TITLE.getTopicTag()).text();
//            desc = extractWhenNoClosingTag(topicElement.select(TopicTags.TOPIC_DESCRIPTION.getTopicTag()).html());
//            narr = extractWhenNoClosingTag(topicElement.select(TopicTags.TOPIC_NARRATIVE.getTopicTag()).html());
            String descStr = topicElement.getElementsByTag(TopicTags.TOPIC_DESCRIPTION.getTopicTag()).text();
            String narrativeStr = topicElement.getElementsByTag(TopicTags.TOPIC_NARRATIVE.getTopicTag()).text();
//            String narrativeStr = topicElement.getElementsByTag(TopicTags.TOPIC_NARRATIVE.getTopicTag()).text();
            Pattern numberPattern = Pattern.compile("(\\d+)");
            Matcher numberMatcher = numberPattern.matcher(num);
            String number = "";
            if(numberMatcher.find()) {
                number = numberMatcher.group().trim();
            }

            descStr = descStr.replace("\n"," ");
            Pattern descPattern = Pattern.compile("Description: (.*)Narrative");
            Matcher descMatcher = descPattern.matcher(descStr);
            String desc = "";
            if(descMatcher.find()) {
                desc = descMatcher.group(1).trim();
            }

            String narrative = narrativeStr.replace("\n"," ").replace("Narrative: ","").trim();

            TopicsModel topic = new TopicsModel(number, title, desc, narrative);
            parsedTopicsList.add(topic);
        }

        return parsedTopicsList;
    }

//    private static String extractWhenNoClosingTag(String html) {
//        return html.split(NEWLINE, 2)[0].split(COLON, 2)[1].replaceAll(LINEFEED, WHITESPACE).trim();
//    }

}
