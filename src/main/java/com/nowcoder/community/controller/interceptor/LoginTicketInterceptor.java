package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.sevice.UserService;
import com.nowcoder.community.util.CookieUtil;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author 王修豪
 * @version 1.0
 */
@Component
public class LoginTicketInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中获取凭证
        String ticket = CookieUtil.getValue(request,"ticket");

        if(ticket!=null){
            //登陆的时候要查询ticket，从而找到用户的数据
            LoginTicket loginTicket=userService.findLoginTicket(ticket);
            //判断是否存在 有没有效 0是有效 1是无效 超时时间晚于当前时间
            if(loginTicket!=null&&loginTicket.getStatus()==0&&loginTicket.getExpired().after(new Date())){
                // 根据凭证查询用户
                User user = userService.findUserById(loginTicket.getUserId());
                // 在本次请求当中持有用户,需要让多线程并发访问都没有问题，线程隔离
                //想办法把user存在ThreadLocal里，隔离
                hostHolder.setUser(user);
            }
        }
        return true;
    }

    @Override
    //在模板之前调用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //得到当前线程持有的user
        User user = hostHolder.getUser();
        if(user != null && modelAndView !=null){
            modelAndView.addObject("loginUser",user);
            System.out.println("加入loginUser");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
