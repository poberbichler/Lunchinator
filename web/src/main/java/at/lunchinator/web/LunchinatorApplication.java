package at.lunchinator.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author poberbichler
 * @since 12.2014
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class LunchinatorApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(LunchinatorApplication.class, args);
	}
}
