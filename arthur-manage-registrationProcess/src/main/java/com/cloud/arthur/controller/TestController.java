package com.cloud.arthur.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenzhen on 2017/7/28.
 */
@RestController
public class TestController {

    @RequestMapping("/tests")
    @HystrixCommand(fallbackMethod = "getNameFail")
    public String getName() {

        String a = null;
        a.length();
        return "success";
    }

    public String getNameFail() {

        return "fail";
    }

    @RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
    public String getServiceName(@PathVariable(value = "name") String name) {

        return "请求者：" + name + "请求arthur-manage-registrationProcess的test方法";
    }
}
