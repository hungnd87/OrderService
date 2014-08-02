package vn.com.vndirect.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import vn.com.vndirect.cache.OrderCache;
import vn.com.vndirect.model.Order;

@Aspect
public class SaveToCacheAspect {
	
	@Autowired
	OrderCache orderCache;
	
	@AfterReturning(  
			pointcut = "execution(* vn.com.vndirect.service.OrderService.placeOrder(..))",
			returning= "result")
	public void saveOrderToCache(JoinPoint joinPoint, Object result){
		Order order = (Order)joinPoint.getArgs()[0];
		String orderId = (String) result;
		order.setOrderId(orderId);
		orderCache.save(order);
	}
}
