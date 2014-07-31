package vn.com.vndirect.validator;

import vn.com.vndirect.exception.InvalidAccountException;
import vn.com.vndirect.exception.ValidatorException;
import vn.com.vndirect.model.Order;

public class AccountValidator implements Validator{

	@Override
	public void validate(Order order) throws ValidatorException {
		if (order.getAccount() == null) {
			throw new InvalidAccountException();
		}
	}

}
