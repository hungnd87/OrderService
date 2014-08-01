package vn.com.vndirect.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.exception.SimpleException;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.validator.Validator;
public interface OrderService {

	void setRestTemplate(RestTemplate restTemplate);

	String placeOrder(Order order) throws SimpleException;

	void setServiceSenderUrl(String serviceSenderUrl);

	void setOrderServiceMethod(String orderServiceMethod);
	
	void setValidators(List<Validator> validators);
}
