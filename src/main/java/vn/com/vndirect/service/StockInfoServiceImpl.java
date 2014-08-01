package vn.com.vndirect.service;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.exception.ServiceException;
import vn.com.vndirect.model.StockInfo;

public class StockInfoServiceImpl implements StockInfoService {
	private RestTemplate restTemplate;
	private String serviceSenderUrl;
	private String getPriceServiceMethod;
	
	public StockInfoServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public StockInfo getStockInfo(String stockCode) throws ServiceException{
		String url = serviceSenderUrl + getPriceServiceMethod;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		StockInfo res;
		try {
			res = restTemplate.postForObject(url, stockCode, StockInfo.class);
		} catch (RestClientException e) {
			throw new ServiceException();
		}
		return res;
	}

	@Override
	public void setServiceSenderUrl(String serviceSenderUrl) {
		this.serviceSenderUrl = serviceSenderUrl;
	}

	@Override
	public void setGetPriceServiceMethod(String getPriceServiceMethod) {
		this.getPriceServiceMethod = getPriceServiceMethod;
	};
	
	
}
