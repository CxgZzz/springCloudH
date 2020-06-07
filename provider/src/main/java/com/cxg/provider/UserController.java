package com.cxg.provider;

import com.cxg.commonss.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
public class UserController {

    public User getUserById(Integer id){
        User user = new User();
        user.setId(id);
        return user;
    }

    @GetMapping("/user/{ids}")  //假设传过来的ids 1,2,3，
    public List<User> getUserByIds(@PathVariable String ids){
        System.out.println(ids);
        String[] split = ids.split(",");
        List<User> users = new ArrayList<>();
        for (String s:
             split) {
            User u=new User();
            u.setId(Integer.parseInt(s));
            users.add(u);

        }
        return users;
    }
}
