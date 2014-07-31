package vn.com.vndirect.validator;

import vn.com.vndirect.exception.InvalidQuantityException;
import vn.com.vndirect.model.Order;

public class QuantityValidator implements Validator{

	@Override
	public void validate(Order order) throws InvalidQuantityException {
		if (order.getQuantity() <= 0) {
			throw new InvalidQuantityException();
		}
	}

}
