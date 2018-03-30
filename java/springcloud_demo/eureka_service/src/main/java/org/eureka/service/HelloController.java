package org.eureka.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    private final Logger logger = Logger.getLogger(getClass());
    
    @Autowired
    private DiscoveryClient client;
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        List<ServiceInstance> instances = client.getInstances("eureka-hello-service");
        for(ServiceInstance instance : instances) {
            logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        }
        return "Hello World";
    }

}
