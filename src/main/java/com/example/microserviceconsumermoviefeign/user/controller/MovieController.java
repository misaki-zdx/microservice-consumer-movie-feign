package com.example.microserviceconsumermoviefeign.user.controller;

import com.example.microserviceconsumermoviefeign.user.entity.User;
import com.example.microserviceconsumermoviefeign.user.service.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Miskai
 * @date 2019/3/11
 */

@RequestMapping("/movies")
@RestController
public class MovieController {
    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/users/{id}")
    public User findById(@PathVariable("id") Long id){
        return this.userFeignClient.findById(id);
    }
}
