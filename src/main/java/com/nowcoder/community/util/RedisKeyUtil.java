package com.nowcoder.community.util;

/**
 * @author 王修豪
 * @version 1.0
 */
public class RedisKeyUtil {
    private static final String SPLIT=":";//以冒号来分割各种单词
    private static final String PREFIX_ENTITY_LIKE="like:entity";//实体前缀
    private static final String PREFIX_USER_LIKE = "like:user";//用户前缀
    private static final String PREFIX_FOLLOWEE = "followee";
    private static final String PREFIX_FOLLOWER = "follower";
    private static final String PREFIX_KAPTCHA = "kaptcha";
    private static final String PREFIX_TICKET = "ticket";
    private static final String PREFIX_USER = "USER";
    //某个实体的赞
    // like:entity:entityType:entityId -> set(userId)存点赞的id
    public static String getEntityLikeKey(int entityType,int entityId){
        return PREFIX_ENTITY_LIKE+SPLIT+entityType+SPLIT+entityId;
    }
    //某个用户的赞
//    like:user:userId ->int
    public static String getUserLikeKey(int userid){
        return PREFIX_USER_LIKE+SPLIT+userid;
    }

    // 为了方便统计，需要两份数据
    // 某个用户关注的实体
    // followee:userId:entityType -> zset(entityId,now)
    public static String getFolloweeKey(int userId,int entityType){
        return PREFIX_FOLLOWEE+SPLIT+userId+SPLIT+entityType;
    }

    // 某个实体拥有的粉丝
    // follower:entityType:entityId -> zset(userId,now)
    public static String getFollowerKey(int entityType,int entityId){
        return PREFIX_FOLLOWER+SPLIT+entityType+SPLIT+entityId;
    }

    // 登录验证码
    public static String getKaptchaKey(String owner){
        return PREFIX_KAPTCHA+SPLIT+owner;
    }
    //登录凭证
    public static String getTicketKey(String ticket){
        return PREFIX_TICKET+SPLIT+ticket;
    }

    public static String getUserKey(int userId){
        return PREFIX_USER+SPLIT+userId;
    }


}
