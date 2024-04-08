package com.nowcoder.community.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author 王修豪
 * @version 1.0
 */
@Configuration
public class KaptchaConfig {
    @Bean
    //核心接口是Producer 有一个实现类DefaultKaptcha
    //实例化一个实现类
    public Producer kaptchaProducer(){
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width","100");
        properties.setProperty("kaptcha.image.height","40");
        properties.setProperty("kaptcha.textproducer.font.size","32");
        properties.setProperty("kaptcha.textproducer.font.color","0,0,0");
        properties.setProperty("kaptcha.textproducer.char.string","01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        properties.setProperty("kaptcha.textproducer.char.length","4");
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");//采用哪种噪声类



        DefaultKaptcha kaptcha = new DefaultKaptcha();
        //需要传入一些参数
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
