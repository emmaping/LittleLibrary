package com.littlelibrary;

import com.littlelibrary.domain.User;
import com.littlelibrary.service.LibraryService;
import com.littlelibrary.service.UserService;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner
{
  @Autowired
  private UserService userService;

  @Autowired
  private LibraryService libraryService;

  public static void main(String[] args)
  {
    ApplicationContext ctx = SpringApplication.run(Application.class, args);

    String[] beanNames = ctx.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (String beanName : beanNames)
    {
      System.out.println(beanName);
    }

  }

  @Override
  public void run(String... args) throws Exception
  {
    User user = userService.getUserByUserName("emmaping");
    System.out.println("----------User Exist : " + user);
    if (null == user)
    {
      user = userService.add(new User("emmaping", "slmftxke", "emma_ping@symantec.com"));
      System.out.println("----------Add an employee to database : " + user);
    }

    // List<User> users = new ArrayList<User>();
    // users.add(user);
    //
    // Library library = new Library("My second borrowed book", 3, 3, users);
    // libraryService.add(library);

  }
}
