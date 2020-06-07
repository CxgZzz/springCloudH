package com.cxg.consumer;

import com.cxg.commons.User;
import org.bouncycastle.util.encoders.UrlBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * @author CxgZzz
 * @version 1.0.0
 * @ClassName UseHelloController
 * @Description
 * @createTime 2020/5/19 19:39
 */

@RestController
public class UseHelloController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/hello1")
    public String hello1() throws IOException {
        HttpURLConnection httpURLConnection= null;
        URL url = new URL("http://localhost:1113/hello");
        httpURLConnection = (HttpURLConnection) url.openConnection();
        System.out.println("---------"+httpURLConnection.getResponseCode());
        if (httpURLConnection.getResponseCode()==200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String s = br.readLine();
            br.close();
            return s;
        }else {
            return "error";
        }
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello3")
    public String hello2() throws IOException {



        String s = restTemplate.getForObject("http://provider/hello", String.class);return s;

//        HttpURLConnection httpURLConnection= null;
//       // URL url = new URL("http://localhost:1113/hello");
//        URL url = new URL(urlString);
//        httpURLConnection = (HttpURLConnection) url.openConnection();
//        System.out.println("---------"+httpURLConnection.getResponseCode());
//        if (httpURLConnection.getResponseCode()==200) {
//            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//            String s = br.readLine();
//            br.close();
//            return s;
//        }else {
//            return "error";
//        }
    }

    @GetMapping("/hello6")
    public void hello6(){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>() ;
        map.add("username","CxgZzz");
        map.add("password","CxgZzzPassword");
        map.add("id",99);
        User user = restTemplate.postForObject("http://provider/user1", map, User.class);
        System.out.println(user.toString());

        user.setId(9899);
        user = restTemplate.postForObject("http://provider/user2", user, User.class);
        System.out.println(user.toString());
    }

    @GetMapping("/hello7")
    public void hello7(){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>() ;
        map.add("username","CxgZzz");
        map.add("password","CxgZzzPassword");
        map.add("id",99);
        URI uri = restTemplate.postForLocation("http://provider/register",map);
        System.out.println(uri);
        String forObject = restTemplate.getForObject(uri, String.class);
        System.out.println(forObject);
    }

    @GetMapping("/hello8")
    public void hello8(){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>() ;
        map.add("username","CxgZzzPut");
        map.add("password","CxgZzzPassword");
        map.add("id",991);
        restTemplate.put("http://provider/user1",map);

        User user = new User();
        user.setId(9090);
        user.setUsername("zahng");
        user.setPassword("fsadfas");
        restTemplate.put("http://provider/user2",user);

    }

    @GetMapping("/hello9")
    public void hello9(){

        restTemplate.delete("http://provider/user1?id={1}",996);

        restTemplate.delete("http://provider/user2/{1}",889);
    }
}
