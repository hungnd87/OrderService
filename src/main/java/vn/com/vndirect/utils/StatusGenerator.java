package vn.com.vndirect.utils;

import vn.com.vndirect.exception.ValidatorException;
import vn.com.vndirect.model.OrderStatus;

public interface StatusGenerator {
	OrderStatus genarateSatus(ValidatorException e);
}
