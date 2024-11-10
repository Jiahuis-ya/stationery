package com.itheima.test;
import com.itheima.config.SpringConfig;
import com.itheima.domain.Stationery;
import com.itheima.domain.User;
import com.itheima.domain.StationeryInfo;
import com.itheima.service.*;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import entity.PageResult;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class MyTest {
//   @Autowired
//   private StationeryService stationeryService;

  @Test
  public void findStationeryById() {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService userService = context.getBean("userService", UserService.class);
    Integer a = userService.checkName("张三");
//    System.out.println("herea" + a);
//     Integer b = userService.checkEmail("111@163.com");
//    System.out.println();
//     System.out.println("hereb" + b);
  }
}





