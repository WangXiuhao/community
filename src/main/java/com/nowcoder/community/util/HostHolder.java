package com.nowcoder.community.util;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author 王修豪
 * @version 1.0
 * 作用是持有用户的信息，用于替代session对象
 */

@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();//有get 和 set方法

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }
    public void clear(){
        users.remove();
    }

}
