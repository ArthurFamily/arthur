package com.cloud.arthur.controller;

import com.cloud.arthur.bean.Users;
import com.cloud.arthur.feignService.RegistrationProcessFeignService;
import com.cloud.arthur.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chenzhen on 2017/7/28.
 */
@RestController
public class TestController {

    @Autowired
    private RegistrationProcessFeignService registrationProcessFeginService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/hello")
    public String hello(String name) {
        return registrationProcessFeginService.getServiceName(name);
    }

    @RequestMapping("/getName")
    public Users getName(String name) {
        return usersService.getUserByName(name);
    }

    @RequestMapping("/getUserList")
    public Object getUserList(int offset) {

        // 分页通过request传入参数
        // PageHelper.offsetPage(offset, 1);

        return new PageInfo(usersService.getUserList(offset,2));
    }
}
