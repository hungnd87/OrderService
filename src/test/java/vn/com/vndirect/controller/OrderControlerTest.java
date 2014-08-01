package vn.com.vndirect.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.model.OrderResult;
import vn.com.vndirect.model.OrderType;
import vn.com.vndirect.model.StatusCode;
import vn.com.vndirect.model.StockInfo;
import vn.com.vndirect.service.OrderService;
import vn.com.vndirect.service.OrderServiceImpl;
import vn.com.vndirect.utils.StatusGeneratorImpl;
import vn.com.vndirect.validator.AccountValidator;
import vn.com.vndirect.validator.PriceGreaterThanZeroValidator;
import vn.com.vndirect.validator.QuantityValidator;
import vn.com.vndirect.validator.SymbolValidator;
import vn.com.vndirect.validator.Validator;

public class OrderControlerTest {
	
	private RestTemplate restTemplate;
	private OrderService orderService;
	
	@Before
	public void setup(){
		restTemplate = new RestTemplate();
		orderService = new OrderServiceImpl(restTemplate);
		List<Validator> validators = new ArrayList<Validator>();
		validators.add(new AccountValidator());
		validators.add(new SymbolValidator());
		validators.add(new PriceGreaterThanZeroValidator());
		validators.add(new QuantityValidator());
		orderService.setValidators(validators);
	}
	
	
	
	@Test
	public void testCallBindParametter() {
		String url = "http://localhost:8080/orderservice/placeorder/account:hung/symbol:VND/price:90/quantity:90/orderType:90";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		RestTemplate template = new RestTemplate();

		StockInfo res = template.getForObject(url, StockInfo.class);
		Assert.assertNotNull(res);
		Assert.assertEquals(true, true);
	}
	
	@Test
	public void testPlaceOrderWithInvalidParam() {
		OrderController orderController = new OrderController();
		orderController.setOrderService(orderService);
		orderController.setStatusGenerator(new StatusGeneratorImpl());
		OrderResult result = orderController.placeOrder(null, "VND", 90, 90, OrderType.ATC.getCode());
		Assert.assertEquals(StatusCode.INVALID_ACCOUNT.getCode(), result.getOrderStatus().getStatus());
	}
} 
