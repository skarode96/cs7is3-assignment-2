package ie.tcd.newssearch.topicsparser;

public class TopicsModel {
    private String topicNum;
    private String topicTitle;
    private String topicDesc;
    private String topicNarrative;

    /** Object to store
     * @param topicNum  
     * @param topicTitle  
     * @param topicDesc  
     * @param topicNarrative  
     **/
    public TopicsModel(String topicNum, String topicTitle, String topicDesc, String topicNarrative) {
        this.topicNum = topicNum;
        this.topicTitle = topicTitle;
        this.topicDesc = topicDesc;
        this.topicNarrative = topicNarrative;

    }

    public String getTopicNum() { return topicNum; }

    public void setTopicNum(String topicNum) { this.topicNum = topicNum; }

    public String getTopicTitle() { return topicTitle; }

    public void setTopicTitle(String topicTitle) { this.topicTitle = topicTitle; }

    public String getTopicDesc() { return topicDesc; }

    public void setTopicDesc(String topicDesc) { this.topicDesc = topicDesc; }

    public String getTopicNarrative() { return topicNarrative; }

    public void setTopicNarrative(String topicNarr) { this.topicNarrative = topicNarr; }

    @Override
    public String toString() {
        return "Topic {" +
                "num:" + this.topicNum +
                ", title:" + this.topicTitle +
                ", desc:" + this.topicDesc +
                ", narr:" + this.topicNarrative +
                "}";
    }
}

enum TopicTags {

    TOPIC_TAG("top"),
    TOPIC_START("<top>"),
    TOPIC_END("</top>"),
    TOPIC_NUM("num"),
    TOPIC_TITLE("title"),
    TOPIC_DESCRIPTION("desc"),
    TOPIC_NARRATIVE("narr");

    private String tag;

    TopicTags(String topictag) {
        this.tag = topictag;
    }

    public String getTopicTag() {
        return tag;
    }
}
