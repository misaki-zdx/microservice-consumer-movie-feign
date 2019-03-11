package com.example.microserviceconsumermoviefeign.user.service;

import com.example.microserviceconsumermoviefeign.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Miskai
 * @date 2019/3/11
 */

@FeignClient(name = "user-server")
public interface UserFeignClient {
    @GetMapping("/users/{id}")
    /**
     *  Feign 中的pathvariable必须得用value指定参数
     */
    User findById(@PathVariable("id") Long id);
}
