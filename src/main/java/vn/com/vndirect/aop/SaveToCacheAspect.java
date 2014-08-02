package vn.com.vndirect.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import vn.com.vndirect.cache.OrderCache;
import vn.com.vndirect.model.Order;

@Aspect
public class SaveToCacheAspect {
	
	@Autowired
	OrderCache orderCache;
	
	@AfterReturning(  
			pointcut = "execution(* vn.com.vndirect.service.OrderService.placeOrder(..))",
			returning= "result")
	public void saveSucsessOrder(JoinPoint joinPoint, Object result){
		if (StringUtils.isEmpty(result)) return;
		Order order = (Order)joinPoint.getArgs()[0];
		String orderId = (String) result;
		order.setOrderId(orderId);
		order.setValue(order.getPrice()*order.getQuantity());
		orderCache.save(order);
	}
}
