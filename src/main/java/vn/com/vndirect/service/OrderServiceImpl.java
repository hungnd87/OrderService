package vn.com.vndirect.service;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.model.Order;

public class OrderServiceImpl implements OrderService {

	private RestTemplate restTemplate;

	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	@Override
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public OrderServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public String placeOrder(Order order) {
		String url = "http://localhost:8080/orderservice/order";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		String res = restTemplate.postForObject(url, order, String.class);
		return res;
	}

}
