package x.y.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	private final static Log log = LogFactory.getLog(OrderService.class);
	
	public void processOrder() {
		log.info("enter OrderService.processOrder");
	}

}
