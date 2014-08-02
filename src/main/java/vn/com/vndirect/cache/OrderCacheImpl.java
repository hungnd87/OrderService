package vn.com.vndirect.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.comparator.OrderComparator;
import vn.com.vndirect.model.Order;

public class OrderCacheImpl implements OrderCache{
	
	Map<String, List<Order>> ordersAccountMap;
	
	@Autowired
	OrderComparator orderComparator;
	
	TreeSet<Order> orderTree;
	
	public OrderCacheImpl() {
		
	}
	
	public void init(){
		this.ordersAccountMap = new HashMap<String, List<Order>>();
		this.orderTree = new TreeSet<Order>(orderComparator);
	}
	
	@Override
	public void save(Order order) {
		List<Order> orders = getOrdersByAccount(order.getAccount());
		orders.add(order);
		orderTree.add(order);
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

	@Override
	public List<Order> getTopOrder(int number) {
		List<Order> orders = new ArrayList<Order>();
		int count = 0;
		for (Order order : orderTree.descendingSet()) {
			orders.add(order);
			count++;
			if (count == number) break;
		}
		return orders;
	}

	@Override
	public List<String> getTopAccount(int number) {
		List<String> accounts = new ArrayList<String>();
		int count = 0;
		for (Order order : orderTree.descendingSet()) {
			if (accounts.contains(order.getAccount())) continue;
			accounts.add(order.getAccount());
			count++;
			if (count == number) break;
		}
		return accounts;
	}

	public void setOrderComparator(OrderComparator orderComparator) {
		this.orderComparator = orderComparator;
	}

}
