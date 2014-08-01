package vn.com.vndirect.service;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import vn.com.vndirect.model.StockInfo;

public class StockInfoServiceImpl implements StockInfoService {
	private RestTemplate restTemplate;
	private String serviceSenderUrl;
	private String orderServiceMethod;
	
	public StockInfoServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public StockInfo getStockInfo(String stockCode){
		String url = serviceSenderUrl + orderServiceMethod;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		StockInfo res = restTemplate.postForObject(url, stockCode, StockInfo.class);
		return res;
	};
	
	
}
