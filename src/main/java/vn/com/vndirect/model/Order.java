package vn.com.vndirect.model;

import java.io.Serializable;

public class Order implements Serializable {
	private String account;
	private String symbol;
	private double price;
	private int quantity;
	private OrderType orderType;
	
	public Order() {

	};
	
	
}
