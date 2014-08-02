package vn.com.vndirect.context;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.exception.SimpleException;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.OrderType;
import vn.com.vndirect.service.OrderService;

@ContextConfiguration(locations = {"classpath:WEB-INF/spring-bean.xml"})
public class ApplicationContextTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private OrderService orderService;
	private String serviceSenderUrl = "sendURl";
	private String orderServiceMethod = "sendMethod";
	@Autowired
	private RestTemplate restTemplate;
	
	@Test 
	@Ignore
	public void initTest() {
		Assert.assertFalse(true);
	}
	
	@Test
	public void testAop() throws SimpleException{
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
		mockServer.expect(requestTo(serviceSenderUrl + orderServiceMethod)).andExpect(method(HttpMethod.POST)).andRespond(withSuccess("10", MediaType.TEXT_PLAIN));
		orderService.setServiceSenderUrl(serviceSenderUrl);
		orderService.setOrderServiceMethod(orderServiceMethod);
		Order order = new Order();
		order.setAccount("hungnd7");
		order.setOrderType(OrderType.ATC.getCode());
		order.setPrice(90);
		order.setQuantity(90);
		order.setSymbol("VND");
		String id = orderService.placeOrder(order); 
	}
}
