package vn.com.vndirect.service;

import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.model.Order;
public interface OrderService {

	void setRestTemplate(RestTemplate restTemplate);

	RestTemplate getRestTemplate();

	String placeOrder(Order order);
}
