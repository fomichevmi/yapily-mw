package com.mif.interview.yapily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class YapilyApplication implements WebMvcConfigurer, ApplicationContextAware {

  public static void main(String[] args) {
    SpringApplication.run(YapilyApplication.class, args);
    System.out.println("I'm alive");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
  }
}
