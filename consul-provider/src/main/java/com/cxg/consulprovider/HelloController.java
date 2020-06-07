package com.cxg.consulprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CxgZzz
 * @version 1.0.0
 * @ClassName HelloController
 * @Description
 * @createTime 2020/5/23 15:52
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    Integer port;
    @GetMapping("/hello")
    public String hello(){
        return "hello>>>>:"+port;
    }
}
