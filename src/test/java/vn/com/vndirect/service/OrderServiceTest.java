package vn.com.vndirect.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.exception.InvalidAccountException;
import vn.com.vndirect.exception.InvalidPriceException;
import vn.com.vndirect.exception.InvalidQuantityException;
import vn.com.vndirect.exception.InvalidSymbolException;
import vn.com.vndirect.exception.OutOfBoundPriceException;
import vn.com.vndirect.exception.ServiceException;
import vn.com.vndirect.exception.SimpleException;
import vn.com.vndirect.exception.ValidatorException;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.OrderType;
import vn.com.vndirect.model.StockInfo;
import vn.com.vndirect.service.OrderService;
import vn.com.vndirect.service.OrderServiceImpl;
import vn.com.vndirect.validator.AccountValidator;
import vn.com.vndirect.validator.OutOfBoundPriceValidator;
import vn.com.vndirect.validator.PriceGreaterThanZeroValidator;
import vn.com.vndirect.validator.QuantityValidator;
import vn.com.vndirect.validator.SymbolValidator;
import vn.com.vndirect.validator.Validator;

public class OrderServiceTest {
	private String serviceSenderUrl;
	private String orderServiceMethod;
	private RestTemplate restTemplate;
	private OrderService orderService;
	
	
	@Before
	public void setup(){
		serviceSenderUrl = "http://localhost:8080/orderservice";
		orderServiceMethod = "placeOrder";
		restTemplate = new RestTemplate();
		orderService = new OrderServiceImpl(restTemplate);
		orderService.setServiceSenderUrl(serviceSenderUrl);
		orderService.setOrderServiceMethod(orderServiceMethod);
		List<Validator> validators = new ArrayList<Validator>();
		validators.add(new AccountValidator());
		validators.add(new SymbolValidator());
		validators.add(new PriceGreaterThanZeroValidator());
		validators.add(new QuantityValidator());
		validators.add(new OutOfBoundPriceValidator(new StockInfoService() {
			
			@Override
			public void setServiceSenderUrl(String serviceSenderUrl) {
			}
			
			@Override
			public void setGetPriceServiceMethod(String getPriceServiceMethod) {
			}
			
			@Override
			public StockInfo getStockInfo(String stockCode) throws ServiceException {
				StockInfo stockInfo = new StockInfo();
				stockInfo.setCeilingPrice(90);
				stockInfo.setFloorPrice(80);
				stockInfo.setSymbol("VND");
				return stockInfo;
			}
		}));
		orderService.setValidators(validators);
	}
	
	@Test
	public void testPlaceOrderWithValidOrder() throws InterruptedException, SimpleException{
		
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo(serviceSenderUrl + orderServiceMethod)).andExpect(method(HttpMethod.POST)).andRespond(withSuccess("10", MediaType.TEXT_PLAIN));

		Order order = new Order();
		order.setAccount("hungnd7");
		order.setOrderType(OrderType.ATC.getCode());
		order.setPrice(90);
		order.setQuantity(90);
		order.setSymbol("VND");
		String id = orderService.placeOrder(order); 
		
		Thread.sleep(100);
		mockServer.verify();
		Assert.assertEquals("10", id);
	}
	
	@Test(expected = InvalidAccountException.class)  
	public void testPlaceOrderWithInvalidAccount() throws SimpleException{
		Order order = new Order();
		orderService.placeOrder(order);
	}
	
	@Test(expected = InvalidSymbolException.class)  
	public void testPlaceOrderWithInvalidSymbol() throws SimpleException{
		Order order = new Order();
		order.setAccount("hiop");
		orderService.placeOrder(order);
	}
	
	@Test(expected = InvalidPriceException.class)  
	public void testPlaceOrderWithInvalidPrice() throws SimpleException{
		Order order = new Order();
		order.setAccount("hungnd7");
		order.setOrderType(OrderType.ATC.getCode());
		order.setSymbol("VTC");
		orderService.placeOrder(order);
	}
	
	@Test(expected = OutOfBoundPriceException.class)  
	public void testPlaceOrderWithOutOfBoundPrice() throws SimpleException{
		Order order = new Order();
		order.setAccount("hungnd7");
		order.setOrderType(OrderType.ATC.getCode());
		order.setSymbol("VTC");
		order.setPrice(190);
		order.setQuantity(200);
		orderService.placeOrder(order);
	}
	
	@Test(expected = InvalidQuantityException.class)  
	public void testPlaceOrderWithInvalidQuantity() throws SimpleException{
		Order order = new Order();
		order.setAccount("hungnd7");
		order.setOrderType(OrderType.ATC.getCode());
		order.setSymbol("VTC");
		order.setPrice(90);
		orderService.placeOrder(order);
	}
	
	
	@Test(expected = InvalidAccountException.class)  
	public void testPlaceOrderWithInvalidOrderType() throws SimpleException{
		Order order = new Order();
		orderService.placeOrder(order);
	}

}
