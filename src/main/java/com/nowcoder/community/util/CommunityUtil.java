package com.nowcoder.community.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
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

    public static String getJSONString(int code, String msg, Map<String,Object>map){
        JSONObject json = new JSONObject();
        //传入的参数撞到json中去
        json.put("code",code);
        json.put("msg",msg);
        if (map != null) {
            for(String key:map.keySet()){
                json.put(key,map.get(key));
            }
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code,String msg){
        return getJSONString(code,msg,null);
    }

    public static String getJSONString(int code){
        return getJSONString(code,null,null);
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",23);
        System.out.println(getJSONString(0,"ok",map));
    }


}
