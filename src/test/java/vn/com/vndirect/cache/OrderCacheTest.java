package vn.com.vndirect.cache;

import java.util.List;

import junitx.framework.ListAssert;

import org.junit.Test;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.OrderType;

public class OrderCacheTest {
	@Test
	public void testSaveOrder(){
		 OrderCache cache = new OrderCacheImpl();
		 Order order = new Order("hungnd7", "VND", 90, 90, OrderType.ATC.getCode());
		 cache.save(order);
		 List<Order> ordersByAccount = cache.getOrdersByAccount("hungnd7");
		 ListAssert.assertContains(ordersByAccount, order);
	}
}
