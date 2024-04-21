package com.nowcoder.community.util;

/**
 * @author 王修豪
 * @version 1.0
 */
public class RedisKeyUtil {
    private static final String SPLIT=":";//以冒号来分割各种单词
    private static final String PREFIX_ENTITY_LIKE="like:entity";//实体前缀
    private static final String PREFIX_USER_LIKE = "like:user";//用户前缀
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

}
