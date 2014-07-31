package vn.com.vndirect.model;

public enum OrderType {
	LO("LO"), MP("MP"), ATC("ATC"), ATO("ATO"),
	MOK("MOK"), MAK("MAK"), MTL("MTL");

	private String code;

	private OrderType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
