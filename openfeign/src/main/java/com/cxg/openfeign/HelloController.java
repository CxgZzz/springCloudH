package com.cxg.openfeign;


import com.cxg.commonss.User;
import org.cxg.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;



    @GetMapping("/helloa")
    public  String hello() throws UnsupportedEncodingException {
        String s = helloService.hello();

        return s;

    }



}
