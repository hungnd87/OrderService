package vn.com.vndirect.validator;

import vn.com.vndirect.exception.InvalidSymbolException;
import vn.com.vndirect.exception.ValidatorException;
import vn.com.vndirect.model.Order;

public class SymbolValidator implements Validator{

	@Override
	public void validate(Order order) throws ValidatorException {
		if (order.getSymbol() == null) {
			throw new InvalidSymbolException();
		}
	}

}
