package vn.com.vndirect.service;

import vn.com.vndirect.exception.ServiceException;
import vn.com.vndirect.model.StockInfo;

public interface StockInfoService {
	StockInfo getStockInfo(String stockCode) throws ServiceException;
	
	void setServiceSenderUrl(String serviceSenderUrl);

	void setGetPriceServiceMethod(String getPriceServiceMethod);
}
