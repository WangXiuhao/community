package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class) //就以他为配置类,和正式环境中是一样的

class CommunityApplicationTests implements ApplicationContextAware { //哪个要得到容器，就实现这个接口
	private ApplicationContext applicationContext;//为了记录下来
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {//ApplicationContext就是Spring容器
		this.applicationContext = applicationContext;//做记录，可以在其他地方使用了
	 }

	 @Test
	public void testApplicationContext(){
		 System.out.println(applicationContext);
		 AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);//从容器中获取这个类的bean
		 System.out.println(alphaDao.select());

		 alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class);//获取指定bean，还是需要指定类型
		 System.out.println(alphaDao.select());
	 }
}
