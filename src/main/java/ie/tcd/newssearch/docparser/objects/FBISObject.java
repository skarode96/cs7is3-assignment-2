package ie.tcd.newssearch.docparser.objects;

public class FBISObject {

	private String docNo;
	private String ht;
	private String header;
	private String h2;
	private String date;
	private String h3;
	private String ti;
	private String text;

	public FBISObject() {
		super();
	}

	public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

	public String getHt() {
		return ht;
	}

	public void setHt(String ht) {
		this.ht = ht;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getH2() {
		return h2;
	}

	public void setH2(String h2) {
		this.h2 = h2;
	}

	public String getH3() {
		return h3;
	}

	public void setH3(String h3) {
		this.h3 = h3;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTi() {
		return ti;
	}

	public void setTi(String ti) {
		this.ti = ti;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "FBISObject [docNo=" + docNo + ", ht=" + ht + ", header=" + header + ", date=" + date + ", ti=" + ti
				+ ", text=" + text + "]";
	}
}
