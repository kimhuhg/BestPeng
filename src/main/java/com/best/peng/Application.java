package com.best.peng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * 启动类
 * @author zhoupeng
 *
 */
@EnableScheduling  //启用定时任务配置
@SpringBootApplication //等同于 @Configuration @EnableAutoConfiguration @ComponentScan
@ServletComponentScan //spring能够扫描到我们自己编写的servlet和filter。这里是配置Druid使用
public class Application{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

	
	//tomcat部署.. extends SpringBootServletInitializer 
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application.class);
//    }
}
