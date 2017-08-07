package com.cloud.arthur.controller;

import com.cloud.arthur.bean.Users;
import com.cloud.arthur.feignService.RegistrationProcessFeignService;
import com.cloud.arthur.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * controller方法都加入API注解方便测试与API维护.
 * Created by chenzhen on 2017/7/28.
 */
@RestController
public class TestController {

    @Autowired
    private RegistrationProcessFeignService registrationProcessFeginService;

    @Autowired
    private UsersService usersService;

    @ApiIgnore
    @RequestMapping("/hello")
    public String hello(String name) {
        return registrationProcessFeginService.getServiceName(name);
    }

    @ApiOperation("根据name获取用户信息")
    @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/getName", method = RequestMethod.GET)
    public Users getName(String name) {
        return usersService.getUserByName(name);
    }

    @ApiOperation("获取用户分页列表")
    @ApiImplicitParam(name = "offset", value = "当前页数", required = true, dataType = "int", paramType = "query")
    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public Object getUserList(int offset) {

        // 分页通过request传入参数
        // PageHelper.offsetPage(offset, 1);

        return new PageInfo(usersService.getUserList(offset,2));
    }
}
