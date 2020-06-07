package com.cxg.hystrix;

import com.cxg.commonss.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.List;

public class UserBatchCommand extends HystrixCommand<List<User>> {

    private List<Integer> ids;

    private UserService userService;

    protected UserBatchCommand(List<Integer> ids,UserService userService) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("batchCommand")));
        this.ids =ids;
        this.userService = userService;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.getUserByIds(ids);
    }
}
