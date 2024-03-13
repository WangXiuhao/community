package com.nowcoder.community.controller;

import com.nowcoder.community.sevice.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 王修豪
 * @version 1.0
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(){
        return "Hi";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }
}
