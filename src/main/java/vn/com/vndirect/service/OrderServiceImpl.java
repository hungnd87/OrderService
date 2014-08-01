package vn.com.vndirect.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.exception.ServiceException;
import vn.com.vndirect.exception.SimpleException;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.validator.Validator;

public class OrderServiceImpl implements OrderService {

	private RestTemplate restTemplate;
	private String serviceSenderUrl;
	private String orderServiceMethod;
	private List<Validator> validators;	

	public OrderServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public String placeOrder(Order order) throws SimpleException {
		validateOrder(order);
		String url = serviceSenderUrl + orderServiceMethod;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String res;
		try {
			res = restTemplate.postForObject(url, order, String.class);
		} catch (RestClientException e) {
			throw new ServiceException();
		}
		return res;
	}

	private void validateOrder(Order order) throws SimpleException {
		for (Validator validator : validators) {
			validator.validate(order);
		}
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

	@Override
	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}
	
}
