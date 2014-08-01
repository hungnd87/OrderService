package vn.com.vndirect.model;

import java.io.Serializable;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private String account;
	private String symbol;
	private double price;
	private int quantity;
	private String orderType;

	public Order() {

	}

	public Order(String account, String symbol, double price,
			int quantity, String orderType) {
		this.account = account;
		this.symbol = symbol;
		this.price = price;
		this.quantity = quantity;
		this.orderType = orderType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	};

}
