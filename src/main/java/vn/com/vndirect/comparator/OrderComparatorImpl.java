package vn.com.vndirect.comparator;

import java.util.Comparator;

import vn.com.vndirect.model.Order;

public class OrderComparatorImpl implements OrderComparator {

	@Override
	public int compare(Order o1, Order o2) {
		
		if (o1.getValue() > o2.getValue()) {
			return 1;
		}
		if (o1.getValue() < o2.getValue()) {
			return -1;
		}
		return o1.getOrderId().compareTo(o2.getOrderId());
	}

}
