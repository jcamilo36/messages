package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring Boot application configuration and main class.
 */
@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration
public class MessagesApplication extends SpringBootServletInitializer {

  /**
   * Main entry point.
   * @param args Program arguments
   */
  public static void main(final String[] args) {
    ApplicationContext ctx = SpringApplication.run(MessagesApplication.class, args);
    DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
    dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
  }
}
