package ie.tcd.newssearch.docparser.tags;

public enum FTTags {
	DOC("<DOC>"), 
	DOCNO("<DOCNO>"),
    PROFILE("<PROFILE>"),
	HEADLINE("<HEADLINE>"), 
    BYLINE("<BYLINE>"),
    DATELINE("<DATELINE>"),
	DATE("<DATE>"),
	PUB("<PUB>"),
	PAGE("<PAGE>"),
	TEXT("<TEXT>");
	
	String tag;
	
	FTTags(String tag) {
		this.tag = tag; 
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
