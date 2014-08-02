package vn.com.vndirect.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SaveToCacheAspect {
	
	@AfterReturning(  
			pointcut = "execution(* vn.com.vndirect.service.OrderService.placeOrder(..))",
			returning= "result")
	public void saveOrderToCache(JoinPoint joinPoint, Object result){
		System.out.println("aaaaa");
	}
}
