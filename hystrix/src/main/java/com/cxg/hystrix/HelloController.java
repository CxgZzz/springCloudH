package com.cxg.hystrix;


import com.cxg.commonss.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.HystrixCommandFactory;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author CxgZzz
 * @version 1.0.0
 * @ClassName HelloController
 * @Description
 * @createTime 2020/5/24 14:13
 */

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(){
        return helloService.hello();
    }

//    @GetMapping("/hello")
//    public String hello(){
//        return helloService.hello();
//    }
    @GetMapping("/hello2")
    public void hello2() throws ExecutionException, InterruptedException {

        HystrixRequestContext hystrixRequestContext = HystrixRequestContext.initializeContext();
        System.out.println("---------start-----");
        HelloCommand helloCommand = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CxgZzz")), restTemplate,"Cxg");
        //两个方式
        String excute = helloCommand.execute();
        System.out.println("----excute--"+excute);
        //
        HelloCommand helloCommand1 = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CxgZzz")), restTemplate,"Cxg");
        Future<String> queue = helloCommand1.queue();
        String s = queue.get();
        System.out.println("hystrix--------"+s);
        hystrixRequestContext.close();
    }

    @GetMapping("/hello3")
    public void hello3() throws ExecutionException, InterruptedException {
        Future<String> hello2 = helloService.hello2();
        String s = hello2.get();
        System.out.println(s);

    }

    @GetMapping("/hello4")
    //表示这个结果会被缓存下来

    public void hello4() throws ExecutionException, InterruptedException {

        HystrixRequestContext hystrixRequestContext =HystrixRequestContext.initializeContext();


        String CxgZzz = helloService.hello3("CxgZzz");
        helloService.delete(CxgZzz);
        CxgZzz=helloService.hello3("CxgZzz");
        hystrixRequestContext.close();
    }

    @Autowired
    UserService userService;

    @GetMapping("/hello5")
    public void hello5() throws ExecutionException, InterruptedException {
        HystrixRequestContext hystrixRequestContext =HystrixRequestContext.initializeContext();
        UserCollapseCommand com1 = new UserCollapseCommand(userService,99);
        UserCollapseCommand com2 = new UserCollapseCommand(userService,98);

        UserCollapseCommand com3 = new UserCollapseCommand(userService,97);
        UserCollapseCommand com4 = new UserCollapseCommand(userService,96);
        UserCollapseCommand com5 = new UserCollapseCommand(userService,95);

        Future<User> q1 = com1.queue();
        Future<User> q2 = com2.queue();
        Future<User> q3 = com3.queue();
        Future<User> q4 = com4.queue();
        Future<User> q5 = com5.queue();
        User u1 = q1.get();
        User u2 = q2.get();
        User u3 = q3.get();
        User u4 = q4.get();
        User u5 = q5.get();
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
        System.out.println(u4);
        System.out.println(u5);
        hystrixRequestContext.close();
    }

    @GetMapping("/hello6")
    public void hello6() throws ExecutionException, InterruptedException {
        HystrixRequestContext hystrixRequestContext =HystrixRequestContext.initializeContext();

        Future<User> q1 = userService.getUserById(99);
        Future<User> q2 = userService.getUserById(98);
        Future<User> q3 = userService.getUserById(97);
        Future<User> q4 = userService.getUserById(92);
        User u1 = q1.get();
        User u2 = q2.get();
        User u3 = q3.get();
        User u4 = q4.get();
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
        System.out.println(u4);
        Future<User> q5 = userService.getUserById(91);
        User u5 = q5.get();
        System.out.println(u5);
        hystrixRequestContext.close();
    }

}
