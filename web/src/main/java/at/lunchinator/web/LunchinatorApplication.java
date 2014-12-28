package at.lunchinator.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * @author poberbichler
 * @since 12.2014
 */
@SpringBootApplication
public class LunchinatorApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(LunchinatorApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LunchinatorApplication.class);
	}
}
