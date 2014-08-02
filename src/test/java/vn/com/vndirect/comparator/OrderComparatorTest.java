package vn.com.vndirect.comparator;

import junit.framework.Assert;

import org.junit.Test;

import vn.com.vndirect.model.Order;

public class OrderComparatorTest {
	@Test
	public void testOrderComparator(){
		Order order1 = new Order();
		Order order2 = new Order();
		
		order1.setOrderId("A");
		order2.setOrderId("B");
		
		order1.setValue(6000);
		order2.setValue(4000);
		
		OrderComparator orderComparator = new OrderComparatorImpl();
		Assert.assertEquals(1, orderComparator.compare(order1, order2));
	}
}
