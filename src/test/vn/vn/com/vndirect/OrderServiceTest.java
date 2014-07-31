package vn.com.vndirect;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.service.OrderService;
import vn.com.vndirect.service.OrderServiceImpl;

@ContextConfiguration(locations = {"classpath://mvc-dispatcher-servlet.xml"})
public class OrderServiceTest extends AbstractJUnit4SpringContextTests{
	@Test
	public void testCallRequest() throws InterruptedException{
		RestTemplate restTemplate = new RestTemplate();
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);


		mockServer.expect(requestTo("http://google.com")).andExpect(method(HttpMethod.GET));
		 OrderService orderService = new OrderServiceImpl(restTemplate);
		 Order order = new Order();
		 String id = orderService.placeOrder(order);
		 Thread.sleep(100);
		 mockServer.verify();
		 Assert.assertNotNull(id);
	}

	private RequestMatcher method(HttpMethod get) {
		// TODO Auto-generated method stub
		return null;
	}

	private RequestMatcher requestTo(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
