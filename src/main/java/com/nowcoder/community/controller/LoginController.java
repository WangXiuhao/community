package com.nowcoder.community.controller;

import com.nowcoder.community.entity.User;
import com.nowcoder.community.sevice.UserService;
import com.nowcoder.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author 王修豪
 * @version 1.0
 */
@Controller
public class LoginController implements CommunityConstant {
    @Autowired
    private UserService userService;
    @RequestMapping(path = "/register",method = RequestMethod.GET)
    public String getRegisterPage(){
        return "/site/register";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String getLoginPage(){
        return "/site/login";
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(Model model, User user){
        Map<String, Object> map = userService.register(user);
        if(map==null||map.isEmpty()){//表示注册成功
            //注册成功之后返回第三方页面
            model.addAttribute("msg","注册成功，我们已经向您的邮箱发送了一封激活邮件，请尽快激活！");
            model.addAttribute("target","/index");
            return "/site/operate-result";
            //返回的是视图名称而不是具体的路径，视图解析器会将其解析为对应的模板文件路径
        }else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            return "/site/register";
        }
    }

    //处理请求的方法
    // http://localhost:8080/community/activation/101/code
    @RequestMapping(path = "/activation/{userId}/{code}",method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId")int userId, @PathVariable("code") String code){
        int result = userService.activation(userId,code);
        if(result==ACTIVATION_SUCCESS) {
            model.addAttribute("msg","激活成功，您的账号已经可以正常使用");
            model.addAttribute("target","/login");
        }else if(result==ACTIVATION_REPEAT){
            model.addAttribute("msg","无效操作，该账号已已激活");
            model.addAttribute("target","/index");
        }else{
            model.addAttribute("msg","激活失败，激活码不正确");
            model.addAttribute("target","/index");
        }
        return "/site/operate-result";
    }
}
