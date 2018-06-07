package com.nick.mbean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SomeBean {
    
    private String someProperties = "Blah, blah";
    
    public String getSomeProperty() {
        return someProperties;
    }
    
    public void setSomeProperty(String sp) {
        this.someProperties = sp;
    }
    
    public String someActionControllerMethod() {
        return "accordion_panel";
    }
    
    public String someOtherActionControllerMethod() {
        return "index";
    }

}
