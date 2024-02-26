package com.icarobteles.webservices;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebservicesApplication {

  public static void main(String[] args) {
    TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
    SpringApplication.run(WebservicesApplication.class, args);
  }

}
