package vn.com.vndirect.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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
import vn.com.vndirect.exception.ValidatorException;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.OrderType;
import vn.com.vndirect.model.StockInfo;
import vn.com.vndirect.service.OrderService;
import vn.com.vndirect.service.OrderServiceImpl;
import vn.com.vndirect.validator.AccountValidator;
import vn.com.vndirect.validator.PriceGreaterThanZeroValidator;
import vn.com.vndirect.validator.QuantityValidator;
import vn.com.vndirect.validator.SymbolValidator;
import vn.com.vndirect.validator.Validator;

public class StockInfoServiceTest {
	private String serviceSenderUrl;
	private String getPriceServiceMethod;
	private RestTemplate restTemplate;
	private StockInfoService stockInfoService;
	
	
	@Before
	public void setup(){
		serviceSenderUrl = "http://localhost:8080/orderservice";
		getPriceServiceMethod = "getPrice";
		restTemplate = new RestTemplate();
		stockInfoService = new StockInfoServiceImpl(restTemplate);
		stockInfoService.setServiceSenderUrl(serviceSenderUrl);
		stockInfoService.setGetPriceServiceMethod(getPriceServiceMethod);
	}
	
	@Test
	public void testPlaceOrderWithValidOrder() throws InterruptedException, ValidatorException, JsonGenerationException, JsonMappingException, IOException{
		StockInfo stockInfo = new StockInfo();
		stockInfo.setBasicprice(90);
		stockInfo.setCeilingPrice(100);
		stockInfo.setFloorPrice(70);
		ObjectMapper mapper = new ObjectMapper();
		String stockInfoJson = mapper.writeValueAsString(stockInfo);
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo(serviceSenderUrl + getPriceServiceMethod)).andExpect(method(HttpMethod.POST)).andRespond(withSuccess(stockInfoJson, MediaType.APPLICATION_JSON));

		StockInfo result = stockInfoService.getStockInfo("VND");
		Thread.sleep(100);
		mockServer.verify();
		Assert.assertEquals(70, result.getFloorPrice(), 0.000);
	}
	

}
