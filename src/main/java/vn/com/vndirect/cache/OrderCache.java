package vn.com.vndirect.cache;

import java.util.List;

import vn.com.vndirect.model.Order;

public interface OrderCache {

	void save(Order order);

	List<Order> getOrdersByAccount(String string);
	List<Order> getTopOrder(int number);
	List<String> getTopAccount(int number);
	

}
