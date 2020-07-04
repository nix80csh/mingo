package kr.mingo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication(scanBasePackages = "kr.mingo")
public class AppConfig extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(AppConfig.class, args);
  }

}
