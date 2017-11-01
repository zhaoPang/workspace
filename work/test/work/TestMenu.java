package work;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bjsxt.pojo.Menu;
import com.bjsxt.service.MenuService;

public class TestMenu {
	@Test
	public void testFindMenuByParentId(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-*.xml");
		MenuService menuService = (MenuService) context.getBean("menuService");
		List<Menu> findMenu = menuService.findMenu();
		for (Menu menu : findMenu) {
			printlnMenuList(menu, 1);
		}
	}
	public void printlnMenuList(Menu  menu,int n){
		for (int i = 0; i < 4*n; i++) {
			System.out.print("-");
		}
		System.out.println(menu.getShowText());
		if(menu.getChildMenu().size()>0){
			for (Menu menu2 : menu.getChildMenu()) {
				printlnMenuList(menu2,n+1);
			}
		}
	}
	
	
}
