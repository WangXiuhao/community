package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate")//这是访问数据库的bean注解
public class AlphaDapHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}
