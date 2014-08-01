package vn.com.vndirect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.vndirect.exception.ValidatorException;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.OrderResult;
import vn.com.vndirect.model.OrderStatus;
import vn.com.vndirect.model.StatusCode;
import vn.com.vndirect.service.OrderService;
import vn.com.vndirect.utils.StatusGenerator;

@Controller
public class OrderController {
	OrderService orderService;
	@Autowired
	StatusGenerator statusGenerator;

	@RequestMapping(value = "/placeorder", method = RequestMethod.GET)
	
	public @ResponseBody OrderResult placeOrder(
			@PathVariable("account") String account,
			@PathVariable("symbol") String symbol,
			@PathVariable("price") double price,
			@PathVariable("quantity") int quantity,
			@PathVariable("orderType") String orderType) {
		
		Order order = new Order(account, symbol, price, quantity, orderType );
		
		String id;
		OrderStatus status = new OrderStatus();
		try {
			id = orderService.placeOrder(order);
			status.setStatus(StatusCode.OK.getCode());
		} catch (ValidatorException e) {
			id = "";
			status = statusGenerator.genarateSatus(e);
		}
		
		OrderResult result = new OrderResult(id, status);
		
		return result;

	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setStatusGenerator(StatusGenerator statusGenerator) {
		this.statusGenerator = statusGenerator;
	}
}
