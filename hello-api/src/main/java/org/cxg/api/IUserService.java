package org.cxg.api;

import com.cxg.commonss.User;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public interface IUserService {

    //方法名无所谓
    @GetMapping("/hello") //请求
    String hello();

    @GetMapping("/hello2")
    public  String hello2(@RequestParam("name") String name);

    @PostMapping("/user2")
    User addUser(@RequestBody User user);

    @DeleteMapping("/user2/{id}")
    void deleteUserById(@PathVariable("id") Integer id);


    @GetMapping("/user3")
    void getUserByName(@RequestHeader("name") String name) throws UnsupportedEncodingException;
}
