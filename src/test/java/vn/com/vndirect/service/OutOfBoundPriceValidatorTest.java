package vn.com.vndirect.service;

import org.junit.Before;
import org.junit.Test;

import vn.com.vndirect.exception.OutOfBoundPriceException;
import vn.com.vndirect.exception.SimpleException;
import vn.com.vndirect.exception.ValidatorException;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.StockInfo;
import vn.com.vndirect.validator.OutOfBoundPriceValidator;

public class OutOfBoundPriceValidatorTest {
	StockInfoService stockInfoService;
	
	@Before
	public void setup(){
		stockInfoService = new StockInfoService() {
			
			@Override
			public void setServiceSenderUrl(String serviceSenderUrl) {
				
			}
			
			@Override
			public void setGetPriceServiceMethod(String getPriceServiceMethod) {
				
			}
			
			@Override
			public StockInfo getStockInfo(String stockCode) {
				StockInfo stockInfo = new StockInfo();
				stockInfo.setCeilingPrice(90);
				stockInfo.setFloorPrice(80);
				return stockInfo;
			}
		};
	}
	
	
	@Test(expected = OutOfBoundPriceException.class)  
	public void testValidateWithOutOfBoundPrice() throws SimpleException{
		Order order = new Order();
		order.setSymbol("VNS");
		
		OutOfBoundPriceValidator validator = new OutOfBoundPriceValidator(stockInfoService);
		validator.validate(order);
	}
}
