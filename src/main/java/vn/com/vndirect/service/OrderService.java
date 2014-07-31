package vn.com.vndirect.service;

import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.model.Order;
public interface OrderService {

	void setRestTemplate(RestTemplate restTemplate);

	String placeOrder(Order order);

	void setServiceSenderUrl(String serviceSenderUrl);

	void setOrderServiceMethod(String orderServiceMethod);
}
