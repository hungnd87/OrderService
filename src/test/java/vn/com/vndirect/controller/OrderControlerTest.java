package vn.com.vndirect.controller;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.model.StockInfo;

public class OrderControlerTest {
	@Test
	public void testCallBindParametter() {
		String url = "http://localhost:8080/orderservice/placeorder";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		RestTemplate template = new RestTemplate();

		StockInfo res = template.getForObject(url, StockInfo.class);
		Assert.assertNotNull(res);
		Assert.assertEquals(true, true);
	}
}
