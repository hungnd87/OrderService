package vn.com.vndirect.validator;

import vn.com.vndirect.exception.InvalidSymbolException;
import vn.com.vndirect.exception.OutOfBoundPriceException;
import vn.com.vndirect.exception.SimpleException;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.StockInfo;
import vn.com.vndirect.service.StockInfoService;

public class OutOfBoundPriceValidator implements Validator {
	
	private StockInfoService stockInfoService;
	
	public OutOfBoundPriceValidator (StockInfoService stockInfoService) {
		this.stockInfoService = stockInfoService;
	}
	
	@Override
	public void validate(Order order) throws SimpleException {
		StockInfo stockInfo = stockInfoService.getStockInfo(order.getSymbol());
		if (stockInfo == null) {
			throw new InvalidSymbolException();
		}
		if (stockInfo.getCeilingPrice() < order.getPrice()) {
			throw new OutOfBoundPriceException();
		}
		if (stockInfo.getFloorPrice() > order.getPrice()) {
			throw new OutOfBoundPriceException();
		}
	}

}
