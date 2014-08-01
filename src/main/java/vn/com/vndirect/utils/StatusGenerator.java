package vn.com.vndirect.utils;

import vn.com.vndirect.model.OrderStatus;

public interface StatusGenerator {
	OrderStatus generateStatus(Throwable e);
}
