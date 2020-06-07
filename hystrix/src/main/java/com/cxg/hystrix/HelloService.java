package com.cxg.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author CxgZzz
 * @version 1.0.0
 * @ClassName HelloController
 * @Description
 * @createTime 2020/5/24 14:09
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    //在这个方法中，我们将发起一个远程调用，去调用provider中提供的、hello接口

    /**
     * 但是，这个调用可能会失败，我们在该方法添加注解HystriCommand，提供一个失败返回方法（服务降级）
     * @return
     */

    @HystrixCommand(fallbackMethod = "error",ignoreExceptions = ArithmeticException.class)
    public String hello(){

        return restTemplate.getForObject("http://provider/hello",String.class);
    }

    @HystrixCommand(fallbackMethod = "error")
    public Future hello2(){

        return new AsyncResult<String>(){
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://provider/hello",String.class);
            }
        };
    }

    /**
     * 注意，这个方法要和方法名一致，然后返回值必须一致，否则会失败
     * @return
     */
    public String error(Throwable t){
        return "error 调用失败啦~"+t.getMessage();
    }

    @HystrixCommand(fallbackMethod = "error2")

    @CacheResult
    public String hello3(String name){
    return restTemplate.getForObject("http://provider/hello2?name={1}",String.class,name);
    }

    @HystrixCommand
    @CacheRemove(commandKey = "hello3")
    public String delete(String name){
        return null;
    }

    public String error2(String name){
    return "error--------CxgZzz";
    }
}
