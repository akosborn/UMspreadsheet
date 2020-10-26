package com.umspreadsheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@EnableConfigurationProperties
@SpringBootApplication
public class Application
{
	@Bean
	public ErrorPageRegistrar errorPageRegistrar() {
		return registry -> {
			registry.addErrorPages(
					new ErrorPage(HttpStatus.NOT_FOUND,"/404")
			);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}