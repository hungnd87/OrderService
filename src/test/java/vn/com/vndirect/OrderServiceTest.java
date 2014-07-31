package vn.com.vndirect;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.exception.ValidatorException;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.OrderType;
import vn.com.vndirect.service.OrderService;
import vn.com.vndirect.service.OrderServiceImpl;

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
	}
	
	@Test
	public void testPlaceOrderWithValidOrder() throws InterruptedException{
		
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo(serviceSenderUrl + orderServiceMethod)).andExpect(method(HttpMethod.POST)).andRespond(withSuccess("10", MediaType.TEXT_PLAIN));
		orderService = new OrderServiceImpl(restTemplate);
		orderService.setServiceSenderUrl(serviceSenderUrl);
		orderService.setOrderServiceMethod(orderServiceMethod);

		Order order = new Order();
		order.setAccount("hungnd7");
		order.setOrderType(OrderType.ATC);
		order.setPrice(90);
		order.setQuantity(90);
		order.setSymbol("VND");
		String id = orderService.placeOrder(order);
		Thread.sleep(100);
		mockServer.verify();
		Assert.assertEquals("10", id);
	}
	
	@Test(expected = ValidatorException.class)  
	public void testPlaceOrderWithInvalidAccount(){
		orderService = new OrderServiceImpl(restTemplate);
		Order order = new Order();
		orderService.placeOrder(order);
	}

}
