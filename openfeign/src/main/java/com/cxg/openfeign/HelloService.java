package com.cxg.openfeign;


import com.cxg.commonss.User;
import org.cxg.api.IUserService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@FeignClient(value = "provider",fallbackFactory= HelloServiceFallbackFactory.class) //注解
public interface HelloService extends IUserService {


}
