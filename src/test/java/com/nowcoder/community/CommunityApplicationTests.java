package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.sevice.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;


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

	 @Test
	public void testBeanManagement(){//测试bean管理的方式
		 AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		 System.out.println(alphaService);
		 alphaService = applicationContext.getBean(AlphaService.class);
		 System.out.println(alphaService);
		 //发现bean只实例化一次 如果需要多个实例需要在bean中加注解，不经常这么做 如果要调用的bean是别人的 不方便修改，就需要写一个配置类
	 }

	 @Test
	public void testBeanConfig(){
		 SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		 System.out.println(simpleDateFormat.format(new Date()));
	 }
	 //以上是主动获取的笨拙的方式 是需要了解的底层的

	@Autowired
	@Qualifier("alphaHibernate")//指定bean
	private AlphaDao alphaDao;
	@Autowired
	private AlphaService alphaService;
	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){
		//测试依赖注入
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}

}
