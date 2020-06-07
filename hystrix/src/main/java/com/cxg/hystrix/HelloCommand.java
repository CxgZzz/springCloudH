package com.cxg.hystrix;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author CxgZzz
 * @version 1.0.0
 * @ClassName HelloCommand
 * @Description
 * @createTime 2020/5/24 15:14
 */
public class HelloCommand extends HystrixCommand<String> {

    String name;

    RestTemplate restTemplate;

    public HelloCommand(Setter setter,RestTemplate restTemplate,String name) {
        super(setter);
        this.restTemplate = restTemplate;
        this.name = name;
    }


    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://provider/hello2?name={1}",String.class,name);
    }

    @Override
    protected String getCacheKey() {
        return name;
    }

    //请求失败的时候进行回调
    @Override
    protected String getFallback() {
        return "error extends"+getExecutionException().getMessage();
    }
}
