package vn.com.vndirect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.vndirect.cache.OrderCache;
import vn.com.vndirect.exception.SimpleException;
import vn.com.vndirect.exception.ValidatorException;
import vn.com.vndirect.model.Order;
import vn.com.vndirect.model.OrderResult;
import vn.com.vndirect.model.OrderStatus;
import vn.com.vndirect.model.StatusCode;
import vn.com.vndirect.service.OrderService;
import vn.com.vndirect.utils.StatusGenerator;

@Controller
@RequestMapping("/")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private StatusGenerator statusGenerator;
	
	@Autowired
	private OrderCache orderCache;
	
	private Integer topNumber = 10;

	@RequestMapping(value = "/placeorder/account:{account}/symbol:{symbol}/price:{price}/quantity:{quantity}/orderType:{orderType}", method = RequestMethod.GET)
	public @ResponseBody
	OrderResult placeOrder(@PathVariable("account") String account,
			@PathVariable("symbol") String symbol,
			@PathVariable("price") double price,
			@PathVariable("quantity") int quantity,
			@PathVariable("orderType") String orderType) throws SimpleException {
		Order order = new Order(account, symbol, price, quantity, orderType);

		String id = "9";
		OrderStatus status = new OrderStatus();
		try {
			id = orderService.placeOrder(order);
			status.setStatus(StatusCode.OK.getCode());
		} catch (SimpleException exception) {
			id = "";
			status = statusGenerator.generateStatus(exception);
		}

		OrderResult result = new OrderResult(id, status);

		return result;

	}

	@RequestMapping(value = "/toporder", method = RequestMethod.GET)
	public @ResponseBody
	List<Order> getTopOrder() {
		List<Order> orders = orderCache.getTopOrder(topNumber);
		return orders;
	}

	@RequestMapping(value = "/topaccount", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getTopAccount() {
		List<String> accounts = orderCache.getTopAccount(topNumber);
		return accounts;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public void setStatusGenerator(StatusGenerator statusGenerator) {
		this.statusGenerator = statusGenerator;
	}
}
