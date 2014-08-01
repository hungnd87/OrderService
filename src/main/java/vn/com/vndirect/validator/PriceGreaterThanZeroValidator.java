package vn.com.vndirect.validator;

import vn.com.vndirect.exception.InvalidPriceException;
import vn.com.vndirect.model.Order;

public class PriceGreaterThanZeroValidator implements Validator{

	@Override
	public void validate(Order order) throws InvalidPriceException {
		if (order.getPrice() <= 0) {
			throw new InvalidPriceException();
		}
	}

}
