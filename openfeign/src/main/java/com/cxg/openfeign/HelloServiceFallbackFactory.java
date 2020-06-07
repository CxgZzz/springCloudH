package com.cxg.openfeign;

import com.cxg.commonss.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class HelloServiceFallbackFactory implements FallbackFactory<HelloService> {
    @Override
    public HelloService create(Throwable throwable) {
        return new HelloService() {
            @Override
            public String hello() {
                return"erro";
            }

            @Override
            public String hello2(String name) {
                return "error";
            }

            @Override
            public User addUser(User user) {
                return null;
            }

            @Override
            public void deleteUserById(Integer id) {

            }

            @Override
            public void getUserByName(String name) throws UnsupportedEncodingException {

            }
        };
    }
}
