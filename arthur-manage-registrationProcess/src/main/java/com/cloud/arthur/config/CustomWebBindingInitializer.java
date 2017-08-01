package com.cloud.arthur.config;

import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Created by chenzhen on 2016/2/16.
 */
public class CustomWebBindingInitializer implements WebBindingInitializer {

    public CustomWebBindingInitializer() {
    }

    public void initBinder(WebDataBinder webDataBinder, WebRequest webRequest) {
        webDataBinder.registerCustomEditor(Date.class, new PropertiesEditor());
    }
}
