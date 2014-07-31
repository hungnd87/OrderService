package vn.com.vndirect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.vndirect.model.StockInfo;

@Controller
public class OrderController {

	@RequestMapping(value = "/placeorder", method = RequestMethod.GET)
	
	public @ResponseBody StockInfo getStockInfo() {
		StockInfo stockInfo = new StockInfo(90, 90, 98, "02", "VND");
		return stockInfo;

	}

}
