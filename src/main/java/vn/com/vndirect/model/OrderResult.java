package vn.com.vndirect.model;

import java.io.Serializable;

public class OrderResult implements Serializable {

	private static final long serialVersionUID = -7526430440785362392L;
	private String orderId;
	private OrderStatus orderStatus;

	public OrderResult(String orderId, OrderStatus orderStatus) {
		this.orderId = orderId;
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
