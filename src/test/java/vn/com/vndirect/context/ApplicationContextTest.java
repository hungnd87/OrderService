package vn.com.vndirect.context;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:/WEB-INF/mvc-dispatcher-servlet.xml"})
public class ApplicationContextTest extends AbstractJUnit4SpringContextTests {
	@Test 
	public void initTest() {
		Assert.assertFalse(false);
	}
}
