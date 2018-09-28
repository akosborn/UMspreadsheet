package com.umspreadsheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;

// Configuration for war
@EnableConfigurationProperties
@SpringBootApplication(exclude = SocialWebAutoConfiguration.class)
public class Application
{
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer()
	{
		return ( container ->
		{
			ErrorPage custom404Page = new ErrorPage(HttpStatus.NOT_FOUND,"/404");
			container.addErrorPages(custom404Page);
		});
	}

	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
}

//Configuration for war
//@EnableConfigurationProperties
//@SpringBootApplication(exclude = SocialWebAutoConfiguration.class)
//public class Application extends SpringBootServletInitializer
//{
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer()
//	{
//		return ( container ->
//		{
//			ErrorPage custom404Page = new ErrorPage(HttpStatus.NOT_FOUND,"/404");
//			container.addErrorPages(custom404Page);
//		});
//	}
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
//	{
//		return application.sources(Application.class);
//	}
//
//	public static void main(String[] args)
//	{
//		SpringApplication.run(Application.class, args);
//	}
//}
