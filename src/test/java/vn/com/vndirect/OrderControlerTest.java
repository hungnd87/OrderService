package vn.com.vndirect;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.StockInfo;

public class OrderControlerTest {
	@Test
	public void testCallBindParametter() {
		String url = "http://localhost:8080/orderservice/order";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		RestTemplate template = new RestTemplate();

		Order order = new Order();

		StockInfo res = template.postForObject(url, order, StockInfo.class);
		Assert.assertNotNull(res);
		Assert.assertEquals(true, true);
	}
}
