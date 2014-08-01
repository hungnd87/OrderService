package vn.com.vndirect.main;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class OrderWebApplicationInitializer  implements WebApplicationInitializer {
	private XmlWebApplicationContext appContext;
	
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		appContext = new XmlWebApplicationContext();
	    appContext.setConfigLocation("/WEB-INF/mvc-dispatcher-servlet.xml");

	    ServletRegistration.Dynamic dispatcher =
	    container.addServlet("dispatcher", new DispatcherServlet(appContext));
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.addMapping("/");
	    
	    Thread stopThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				 try {
						Thread.sleep(7000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 System.out.println("stop");
				    appContext.stop();
					appContext.close();
					//appContext.destroy();
				
			}
		});
	    
	    stopThread.start();
	    
	    Thread startThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				 try {
						Thread.sleep(12000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 System.out.println("start");
				 appContext.refresh();
				    appContext.start();
				
			}
		});
	   
	    startThread.start();
	    
	}


}