package vn.com.vndirect.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StockInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private double basicprice;
	private double ceilingPrice;
	private double floorPrice;
	private String floorCode;
	private String symbol;
	
	public StockInfo(double basicprice, double ceilingPrice, double floorPrice,
			String floorCode, String symbol) {
		this.basicprice = basicprice;
		this.ceilingPrice = ceilingPrice;
		this.floorPrice = floorPrice;
		this.floorCode = floorCode;
		this.symbol = symbol;
	}



	public double getBasicprice() {
		return basicprice;
	}

	public void setBasicprice(double basicprice) {
		this.basicprice = basicprice;
	}

	public double getCeilingPrice() {
		return ceilingPrice;
	}

	public void setCeilingPrice(double ceilingPrice) {
		this.ceilingPrice = ceilingPrice;
	}

	public double getFloorPrice() {
		return floorPrice;
	}

	public void setFloorPrice(double floorPrice) {
		this.floorPrice = floorPrice;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
