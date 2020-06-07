package com.cxg.hystrix;

import com.cxg.commonss.User;
import com.netflix.hystrix.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserCollapseCommand extends HystrixCollapser<List<User>,User,Integer> {

    //这个setter和之前不一样，这个时候请求的时间间隔需要自己配置
    public UserCollapseCommand(UserService userService, Integer id) {
        super(HystrixCollapser.Setter.withCollapserKey(HystrixCollapserKey.
                Factory.asKey("UserCollapseCommand")).
                andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(200)));
        this.userService = userService;
        this.id = id;
    }

    private UserService userService;
    private Integer id;

    //真正调用的时候使用的请求参数
    @Override
    public Integer getRequestArgument() {
        return id;
    }


    //这个是请求合并的方法
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Integer>> collection) {

        List<Integer> ids = new ArrayList<>(collection.size());
        for (CollapsedRequest<User, Integer> l: collection
             ) {
            ids.add(l.getArgument());
        }
        return new UserBatchCommand(ids,userService);
    }

    //这个是请求结果分发的方法
    @Override
    //1.请求结果，2.请求
    //将结果分配回去请求
    protected void mapResponseToRequests(List<User> users, Collection<CollapsedRequest<User, Integer>> collection) {
        int count = 0;
        for (CollapsedRequest<User, Integer> res: collection
             ) {
            res.setResponse(users.get(count++));
        }
    }
}
