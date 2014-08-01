package vn.com.vndirect.utils;

import vn.com.vndirect.exception.InvalidAccountException;
import vn.com.vndirect.exception.InvalidOrderTypeException;
import vn.com.vndirect.exception.InvalidPriceException;
import vn.com.vndirect.exception.InvalidQuantityException;
import vn.com.vndirect.exception.InvalidSymbolException;
import vn.com.vndirect.model.OrderStatus;
import vn.com.vndirect.model.StatusCode;

public class StatusGeneratorImpl implements StatusGenerator{
	@Override
	public OrderStatus generateStatus(Throwable e) {
		OrderStatus status = new OrderStatus();
		status.setMessage(e.getClass().toString());
		if (e instanceof InvalidAccountException) {
			status.setStatus(StatusCode.INVALID_ACCOUNT.getCode());
			return status;
		}
		if (e instanceof InvalidOrderTypeException) {
			status.setStatus(StatusCode.INVALID_ORDER_TYPE.getCode());
			return status;
		}
		if (e instanceof InvalidPriceException) {
			status.setStatus(StatusCode.INVALID_PRICE.getCode());
			return status;
		}
		if (e instanceof InvalidQuantityException) {
			status.setStatus(StatusCode.INVALID_QUANTITY.getCode());
			return status;
		}
		if (e instanceof InvalidSymbolException) {
			status.setStatus(StatusCode.INVALID_SYMBOL.getCode());
			return status;
		}
		status.setStatus(StatusCode.FAIL.getCode());
		return status;
	}
}
