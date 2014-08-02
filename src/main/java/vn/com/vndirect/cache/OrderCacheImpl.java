package vn.com.vndirect.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.cache.OrderCache;;

public class OrderCacheImpl implements OrderCache{
	
	Map<String, List<Order>> ordersAccountMap;
	
	public OrderCacheImpl() {
		this.ordersAccountMap = new HashMap<String, List<Order>>();
	}
	
	@Override
	public void save(Order order) {
		List<Order> orders = getOrdersByAccount(order.getAccount());
		orders.add(order);
	}
	

	@Override
	public List<Order> getOrdersByAccount(String account) {
		List<Order> orders = ordersAccountMap.get(account);
		if (orders == null) {
			orders = new ArrayList<Order>();
			ordersAccountMap.put(account, orders);
		}
		return orders;
	}

}
