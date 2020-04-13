package ie.tcd.newssearch.docparser.tags;

public enum FBISTags {
	DOC("<DOC>"), 
	DOCNO("<DOCNO>"),
	HT("<HT>"),
	HEADER("<HEADER>"), 
	H2("<H2>"),
	DATE1("<DATE1>"),
	H3("<H3>"),
	TI("<TI>"),
	TEXT("<TEXT>");
	
	String tag;
	
	FBISTags(String tag) {
		this.tag = tag; 
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
