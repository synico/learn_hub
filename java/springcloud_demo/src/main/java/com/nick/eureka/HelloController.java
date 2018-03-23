package com.nick.eureka;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    private final Logger logger = Logger.getLogger(HelloController.class.getName());
    
    @Autowired
    private DiscoveryClient client;
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        List<ServiceInstance> instances = client.getInstances("hello-service");
        for(ServiceInstance instance : instances) {
            logger.info("/hello, host: " + instance.getHost() + ", service id: " + instance.getServiceId());
        }
        return "Hello World";
    }

}
