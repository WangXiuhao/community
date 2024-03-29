package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author 王修豪
 * @version 1.0
 */
public class CommunityUtil {

    // 生成随机字符串 每次上传文件的时候需要给文件一个随机的名字
    public static String generateUUID(){//
        String s = UUID.randomUUID().toString();//中间有字母和横线所构成，所以需要把横线去掉
        String uuid = s.replaceAll("-", "");
        return uuid;
    }

    // MD5加密
    // hello -> abc123def456
    // hello + 随机字符串 -> 12d23asfawe21
    public static String md5(String key){
        if(StringUtils.isBlank(key)){//如果是空的就不去处理
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());//这个函数要求传入的是Byte
    }


}
