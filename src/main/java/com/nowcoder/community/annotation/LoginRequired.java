package com.nowcoder.community.annotation;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 王修豪
 * @version 1.0
 */
@Target(ElementType.METHOD)//表示这个注解写在方法之上，用于描述方法
@Retention(RetentionPolicy.RUNTIME)//有效时长
public @interface LoginRequired {

}
