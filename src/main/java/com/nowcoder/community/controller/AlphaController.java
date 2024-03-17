package com.nowcoder.community.controller;

import com.nowcoder.community.sevice.AlphaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        //请求行数据 第一行的数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        //请求消息头数据
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+": "+value);
        }
        //请求体数据 假设传一个code进来
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");//设置返回数据的类型 支持中文
        try(
                PrintWriter writer = response.getWriter(); //通过输出流 向浏览器输出

        ) {
            writer.write("<h1>雷欧论坛<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //java7 新的语法 在try的括号里床脚 最后编译会自动创建finally close掉资源
    }
    //Get请求
    //两种获取数据的方式
    //问号 在路径当中


    // /students?current=1&limit=20
    @RequestMapping(path="/students",method = RequestMethod.GET)//明确什么请求
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit" ,required = false,defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some student";
    }
    // /student/123
    @RequestMapping(path = "/student/{id}",method=RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id")int id){
        System.out.println(id);
        return "a student";
    }

    //post请求
    @RequestMapping(path ="/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){//取post请求的参数 直接声明参数,名字一致
        return "success";
    }
    //浏览器响应html数据
    //两种方式
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){//返回model和view
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","张三");
        modelAndView.addObject("age",30);
        modelAndView.setViewName("/demo/view");//templates下级的目录 和模板的名字 不用写后缀 默认html
        return modelAndView;

    }

    @RequestMapping(path = "school",method = RequestMethod.GET)
    public String getSchool(Model model){//dispatcher会自动传参
        model.addAttribute("name","青岛九中");
        model.addAttribute("age",124);
        return "/demo/view";

    }

    // 浏览器响应JSON数据（异步请求）
    // java对象 要返回给浏览器 用JS解析 希望得到JS对象 所以要 Java对象 -->Json字符串 --> Js对象

    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody //因为要返回Json字符串
    public Map<String, Object> getEmp(){//会自动把map转成json字符串
        Map<String,Object>emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        return emp;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody //因为要返回Json字符串
    public List<Map<String, Object>> getEmps(){//会自动把map转成json字符串
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String,Object>emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        list.add(emp);
        emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",22);
        emp.put("salary",9000.00);
        list.add(emp);
        return list;
    }


}
