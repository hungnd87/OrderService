package vn.com.vndirect.validator;

import vn.com.vndirect.exception.SimpleException;
import vn.com.vndirect.model.Order;

public interface Validator {
	void validate(Order order) throws SimpleException;
}
