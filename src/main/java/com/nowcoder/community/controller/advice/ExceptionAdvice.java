package com.nowcoder.community.controller.advice;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 王修豪
 * @version 1.0
 */
//表示只扫描带有Controller注解的bean
@ControllerAdvice(annotations = Controller.class)
public class ExceptionAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    //Exception是异常的父类，所以所有异常都会被处理
    @ExceptionHandler({Exception.class})
    public void handleException(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.error("服务器发生异常："+e.getMessage());
        //异常的详细信息记录下来
        for(StackTraceElement element:e.getStackTrace()){//遍历数组
            logger.error(element.toString());
        }

        //浏览器访问服务器可能是个普通的请求，返回一个页面 也可能是一个异步的请求，返回一个jSon
        String xRequestWith = request.getHeader("x-requested-with");
        if("XMLHttpRequest".equals(xRequestWith)){//说明是异步请求
            response.setContentType("application/plain;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.write(CommunityUtil.getJSONString(1,"服务器异常！"));
        }else{
            response.sendRedirect(request.getContextPath()+"/error");
        }
    }

}
