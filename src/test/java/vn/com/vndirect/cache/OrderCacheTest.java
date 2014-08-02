package vn.com.vndirect.cache;

import java.util.List;

import junit.framework.Assert;
import junitx.framework.ListAssert;

import org.junit.Before;
import org.junit.Test;

import vn.com.vndirect.comparator.OrderComparatorImpl;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.OrderType;

public class OrderCacheTest {
	private OrderCacheImpl cache;

	@Before
	public void setup() {
		cache = new OrderCacheImpl();
		cache.setOrderComparator(new OrderComparatorImpl());
		cache.init();
	}

	@Test
	public void testSaveOrder() {

		Order order = new Order("hungnd7", "VND", 90, 90,
				OrderType.ATC.getCode());
		cache.save(order);
		List<Order> ordersByAccount = cache.getOrdersByAccount("hungnd7");
		ListAssert.assertContains(ordersByAccount, order);
	}

	@Test
	public void testGetTopOrder() {
		Order order1 = new Order("hungnd1", "VND", 90, 2, OrderType.ATC.getCode());
		Order order2 = new Order("hungnd2", "VND", 90, 3, OrderType.ATC.getCode());
		Order order3 = new Order("hungnd3", "VND", 90, 1, OrderType.ATC.getCode());
		order1.setValue(90*2);
		order2.setValue(90*3);
		order3.setValue(90*1);
		
		cache.save(order1);
		cache.save(order2);
		cache.save(order3);
		
		List<Order> order = cache.getTopOrder(10);
		Assert.assertEquals("hungnd2", order.get(0).getAccount());
		Assert.assertEquals("hungnd1", order.get(1).getAccount());
		Assert.assertEquals("hungnd3", order.get(2).getAccount());
	}
	
	@Test
	public void testGetTopAcount() {
		Order order1 = new Order("hungnd1", "VND", 90, 2, OrderType.ATC.getCode());
		Order order2 = new Order("hungnd2", "VND", 90, 3, OrderType.ATC.getCode());
		Order order3 = new Order("hungnd2", "VND", 90, 1, OrderType.ATC.getCode());
		order1.setValue(90*2);
		order2.setValue(90*3);
		order3.setValue(90*1);
		
		cache.save(order1);
		cache.save(order2);
		cache.save(order3);
		
		List<String> order = cache.getTopAccount(10);
		Assert.assertEquals("hungnd2", order.get(0));
		Assert.assertEquals("hungnd1", order.get(1));
		Assert.assertEquals(2, order.size());
	}
}
