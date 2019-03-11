package com.example.microserviceconsumermoviefeign.user.service;

import com.example.microserviceconsumermoviefeign.user.entity.User;
import feign.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Miskai
 * @date 2019/3/11
 */

@FeignClient(name = "user-server")
public interface UserFeignClient {
    //@FeignClient(name = "abcde", url = "http://localhost:8000/") 。此时，name可以是任意值，但不可省略，否则应用将无法启动！
    @GetMapping("/users/{id}")
    /**
     *  Feign 中的pathvariable必须得用value指定参数
     */
    User findById(@PathVariable("id") Long id);

    //假设需请求的URL包含多个参数，例如http://microservice-provider-user/get?id=1&username=张三 ，该如何使用Feign构造呢？

    /**
     * 方法一
     * @FeignClient(name = "microservice-provider-user")
     * public interface UserFeignClient {
     *   @RequestMapping(value = "/get", method = RequestMethod.GET)
     *   public User get1(@RequestParam("id") Long id, @RequestParam("username") String username);
     * }
     */

    /**
     * 方法二
     * 多参数的URL也可使用Map来构建。当目标URL参数非常多的时候，可使用这种方式简化Feign接口的编写。
     *
     * @FeignClient(name = "microservice-provider-user")
     * public interface UserFeignClient {
     *   @RequestMapping(value = "/get", method = RequestMethod.GET)
     *   public User get2(@RequestParam Map<String, Object> map);
     * }
     */

    /**
     * POST请求包含多个参数
     * 下面来讨论如何使用Feign构造包含多个参数的POST请求。假设服务提供者的Controller是这样编写的：
     *
     * @RestController
     * public class UserController {
     *   @PostMapping("/post")
     *   public User post(@RequestBody User user) {
     *     ...
     *   }
     * }
     * 我们要如何使用Feign去请求呢？答案非常简单，示例：
     *
     * @FeignClient(name = "microservice-provider-user")
     * public interface UserFeignClient {
     *   @RequestMapping(value = "/post", method = RequestMethod.POST)
     *   public User post(@RequestBody User user);
     * }
     */
}
/**
     * 该Feign Client的配置类，注意：
     * 1. 该类可以独立出去；
     * 2. 该类上也可添加@Configuration声明是一个配置类；
     * 配置类上也可添加@Configuration注解，声明这是一个配置类；
     * 但此时千万别将该放置在主应用程序上下文@ComponentScan所扫描的包中，
     * 否则，该配置将会被所有Feign Client共享，无法实现细粒度配置！
     * 个人建议：像我一样，不加@Configuration注解
     *
     * @author zhouli
     */
    class UserFeignConfig {
        @Bean
        public Logger.Level logger() {
            return Logger.Level.FULL;
        }
}
