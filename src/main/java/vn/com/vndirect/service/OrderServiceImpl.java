package vn.com.vndirect.service;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.model.Order;

public class OrderServiceImpl implements OrderService {

	private RestTemplate restTemplate;
	private String serviceSenderUrl;
	private String orderServiceMethod;

	public OrderServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public String placeOrder(Order order) {
		String url = serviceSenderUrl + order;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		String res = restTemplate.postForObject(url, order, String.class);
		return res;
	}

	@Override
	public void setServiceSenderUrl(String serviceSenderUrl) {
		this.serviceSenderUrl = serviceSenderUrl;
	}

	public String getServiceSenderUrl() {
		return serviceSenderUrl;
	}

	@Override
	public void setOrderServiceMethod(String orderServiceMethod) {
		this.orderServiceMethod = orderServiceMethod;
	}

	public String getOrderServiceMethod() {
		return orderServiceMethod;
	}

	@Override
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
