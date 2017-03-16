package org.springframework.renewtech;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.xyz.AppConfig;
import com.xyz.dao.OrderDaoService;
import com.xyz.dao.OrderDaoServiceImpl;

/**
 * Hello world!
 *
 */
@SuppressWarnings("all")
public class TestAspect 
{
	private static Log log = LogFactory.getLog(TestAspect.class);
	
    public static void main( String[] args )
    {
//    	OrderDaoService os = new OrderDaoServiceImpl();
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    	OrderDaoService orderDaoService = ctx.getBean(OrderDaoService.class, "orderDaoService");
    	try {
//    		orderDaoService.printOrderInfo();
    		Long orderId = orderDaoService.getOrderId("web133");
    		log.info("The order id: " + orderId);
    	} catch(Throwable ex) {
    		log.info("in catch");
    	} finally {
    		log.info("in finally");
    	}
    }
}
