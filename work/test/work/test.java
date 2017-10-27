package work;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bjsxt.mapper.AddressDao;
import com.bjsxt.mapper.UserDao;
import com.bjsxt.pojo.Address;
import com.bjsxt.service.UserService;

public class test {
	@Test
	public void test01() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
		System.out.println(sqlSessionFactory);
		System.out.println(sqlSessionFactory.openSession());
	}

	@Test
	public void test02() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		System.out.println(userDao);
		AddressDao addressDao = (AddressDao) context.getBean("addressDao");
		System.out.println(addressDao);
	}

	@Test
	public void test03() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		UserService userService = (UserService) context.getBean("userService");
		System.out.println(userService.listUserAddressesById("1"));
	}
	
	@Test
	public void test04() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mybatis-X.xml");
		UserDao userDao = (UserDao)context.getBean("userDao");
		System.out.println(userDao);
	}
	
	@Test
	public void test05() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mybatis-X.xml","applicationContext-tx-X.xml");
		UserService userService = (UserService)context.getBean("userService");
		System.out.println(userService);
	}
	
	@Test
	public void test06() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mybatis-Y.xml","applicationContext-tx-Y.xml");
		UserService userService = (UserService)context.getBean("userService");
		System.out.println(userService);
	}
	
}
