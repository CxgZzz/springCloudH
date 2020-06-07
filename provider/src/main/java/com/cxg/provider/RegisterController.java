package com.cxg.provider;


import com.cxg.commonss.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author CxgZzz
 * @version 1.0.0
 * @ClassName RegisterController
 * @Description
 * @createTime 2020/5/23 10:18
 */
@Controller
public class RegisterController {

    @PostMapping("/register")
    public String register(User user){
        return "redirect:http://provider/loginPage?username="+user.getUsername();
    }

    @GetMapping("/loginPage")
    @ResponseBody
    public String loginPage(String username){
        return "loginPage:"+username;
    }

}
