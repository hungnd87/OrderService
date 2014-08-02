package vn.com.vndirect.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.zk.Application;

public class ZookeeperFilter implements Filter {
	private Application zookeperClient;

	@Override
	public void destroy() {
		zookeperClient.stop();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	    HttpServletResponse res = (HttpServletResponse) response;
		if (zookeperClient.isSlave()){
		  	 res.sendError(HttpServletResponse.SC_BAD_GATEWAY);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		zookeperClient = new Application();
		zookeperClient.start();
	}

}
