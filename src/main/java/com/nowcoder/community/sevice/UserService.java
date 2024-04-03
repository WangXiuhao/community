package com.nowcoder.community.sevice;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 王修豪
 * @version 1.0
 */
@Service
public class UserService implements CommunityConstant {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;//邮件客户端

    @Autowired
    private TemplateEngine templateEngine;//模板引擎

    //注入一个值
    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(int id){
        return userMapper.selectById(id);
    }

    public Map<String,Object> register(User user){
        Map<String,Object> map = new HashMap<>();
        //先对user进行判断
        //空值处理
        if(user==null){
            throw new IllegalArgumentException("参数不能为空");
        }
        if(StringUtils.isBlank(user.getUsername())){
            map.put("usernameMsg","账号内容不能为空！");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg","密码不能为空！");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg","邮箱不能为空！");
            return map;
        }

        //验证账号
        User user1 = userMapper.selectByName(user.getUsername());
        if(user1!=null){
            map.put("usernameMsg","该用户名已存在！");
            return map;
        }
        //验证邮箱
        user1 = userMapper.selectByEmail(user.getEmail());
        if(user1 != null ){
            map.put("emailMsg","该邮箱已被注册！");
            return map;
        }

        //注册邮箱
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));//生成随机字符串，用不了那么长
        //对密码进行加密覆盖
        user.setPassword(CommunityUtil.md5(user.getPassword()+user.getSalt()));
        //普通用户
        user.setType(0);
        //状态0表示没有激活
        user.setStatus(0);
        //激活码
        user.setActivationCode(CommunityUtil.generateUUID());
        //随机头像
        //Random().nextInt(1000) 随机整数在1000之内
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
        //注册的时间
        user.setCreateTime(new Date());
        userMapper.insertUser(user);//添加到库里

        //然后就要给用户发送激活邮件了
        Context context = new Context();
        context.setVariable("email",user.getEmail());
        // http://localhost:8080/community/activation/101/code
        String url = domain + contextPath + "/activation/" + user.getId() + "/"+user.getActivationCode();
        context.setVariable("url",url);
        //生成邮件内容
        String content = templateEngine.process("/mail/activation",context);
        mailClient.sendMail(user.getEmail(),"激活账号",content);
        return map;
    }

    /**
     * 用户激活
     * @param userId
     * @param code
     * @return
     */
    public int activation(int userId,String code){
        //先查询
        User user = userMapper.selectById(userId);
        //重复激活
        if(user.getStatus()==1){
            return ACTIVATION_REPEAT;
        }else if(user.getActivationCode().equals(code)){
            userMapper.updateStatus(userId,1);
            //如果激活码和传入的激活码一致 表示可以激活成功
            return ACTIVATION_SUCCESS;
        }else{
            //不相等就失败了
            return ACTIVATION_FAILURE;
        }
    }

}