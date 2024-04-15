package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author 王修豪
 * @version 1.0
 */
@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);//offset是起始行的行号 limit是每一页最多显示多少数据


    //@Param注解用于给参数取别名
    //如果只有一个参数，并且在<if>里使用，就必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);//给参数起个别名

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);




}
