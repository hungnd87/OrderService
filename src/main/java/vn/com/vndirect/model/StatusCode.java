package vn.com.vndirect.model;

public enum StatusCode {
	OK("202"),
	INVALID_ACCOUNT("404"),
	INVALID_SYMBOL("404"),
	INVALID_PRICE("404"),
	INVALID_QUANTITY("404"),
	INVALID_ORDER_TYPE("404"),
	FAIL("404");
	
	private String code;
	
	private StatusCode(String code){
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
}
