package com.cxg.provider;


import com.cxg.commonss.User;
import org.cxg.api.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

/**
 * @author CxgZzz
 * @version 1.0.0
 * @ClassName HelloController
 * @Description
 * @createTime 2020/5/19 18:52
 */
@RestController
public class HelloController implements IUserService {
    @Override
    public String hello() {
        return "hello cxgCloud";
    }

    @Override
    public String hello2(String name) {
        System.out.println("当前时间---"+name+"----"+new Date());
        return "hello cxgCloud"+name;
    }

    @Override
    public User addUser(User user) {
        return user;
    }

    @Override
    public void deleteUserById(Integer id) {
        System.out.println("key-value----PathVariable-----"+id);
    }

    @Override
    public void getUserByName(String name) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode(name,"utf-8"));
    }
//    @Value("1113")
//    Integer port;
//    @GetMapping("/hello")
//    public String hello(){
//        return "hello cxgCloud";
//    }
//
//    @GetMapping("/hello2")
//    public String hello2(String name){
//
//        System.out.println("当前时间---"+name+"----"+new Date());
//        return "hello cxgCloud"+name;
//    }
//
//    @PostMapping("/user1")
//    public User addUser(User user){
//        return user;
//    }
//
//    @Override
//    public void deleteUserById(Integer id) {
//
//    }
//
//    @PostMapping("/user2")
//    public User addUser2(@RequestBody User user){
//        return user;
//    }
//
//
//
//    @PutMapping("/user1")
//    public void updateUser( User user){
//
//        System.out.println(user.toString());
//    }
//
//
//    @PutMapping("/user2")
//    public void updateUser2( @RequestBody User user){
//
//        System.out.println(user.toString());
//    }
//
//    @DeleteMapping("/user1")
//    public void deleteUser(Integer id){
//        System.out.println("key-value---------"+id);
//    }
//
//    @DeleteMapping("/user2/{id}")
//    public void deleteUser1(@PathVariable Integer id){
//        System.out.println("key-value----PathVariable-----"+id);
//    }
//
//    @GetMapping("/user3")
//    public void getUserByName(@RequestHeader String name) throws UnsupportedEncodingException {
//        System.out.println(URLDecoder.decode(name,"utf-8"));
//    }

}
