package com.nowcoder.community.sevice;

import com.nowcoder.community.dao.AlphaDao;
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Scope("prototype")//可以多个实例
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;

    @PostConstruct//初始化构造器之后调用
    public void init(){
        System.out.println("初始化AlphaService");
    }

    public AlphaService() {
        System.out.println("实例化AlphaService");
    }
    @PreDestroy//在销毁之前调用
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }
}
