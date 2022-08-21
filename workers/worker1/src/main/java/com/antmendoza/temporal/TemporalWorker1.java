package com.antmendoza.temporal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TemporalWorker1 {

  public static void main(String[] args) {

    SpringApplication application = new SpringApplication(TemporalWorker1.class);
    application.setWebApplicationType(WebApplicationType.NONE);
    application.run(args);

    new AppWorker().start(args);


  }
}
