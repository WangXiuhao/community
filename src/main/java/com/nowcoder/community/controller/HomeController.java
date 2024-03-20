package com.nowcoder.community.controller;

import com.nowcoder.community.sevice.DiscussPostService;
import com.nowcoder.community.sevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 王修豪
 * @version 1.0
 * @deprecated 要开始写视图层了
 */
@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model){//model携带数据
//41:12
         return "/index";
    }
}
