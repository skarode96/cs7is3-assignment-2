package ie.tcd.newssearch.topicsparser;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class TopicsParserTest {
    @Test
    public void parse() throws IOException {
        Path currentRelativePath = Paths.get("").toAbsolutePath();
        String pathToTopics = String.format("%s/dataset/topics/topics",currentRelativePath);
        List<TopicsModel> topicList = TopicsParser.parse(pathToTopics);
        Assert.assertEquals(50, topicList.size());
    }
}
