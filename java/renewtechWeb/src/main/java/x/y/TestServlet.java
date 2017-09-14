package x.y;

import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import x.y.service.OrderService;

@SuppressWarnings("all")
public class TestServlet extends HttpServlet implements Servlet {
	
	private final static Log log = LogFactory.getLog(TestServlet.class);
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			if(out != null) {
				out.print("Hello");
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		if(orderService == null) {
			log.info("orderService is null");
		} else {
			log.info("orderService is not null");
		}
		log.info("test");
	}

}
