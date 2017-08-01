package com.cloud.arthur.feignService;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chenzhen on 2017/7/28.
 */
@FeignClient(value = "arthur-manage-registrationProcess")
public interface RegistrationProcessFeignService {

    @RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
    public String getServiceName(@PathVariable(value = "name") String name);
}
