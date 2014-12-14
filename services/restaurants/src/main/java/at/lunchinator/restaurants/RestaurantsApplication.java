package at.lunchinator.restaurants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author poberbichler
 * @since 12.2014
 */
@ComponentScan
@Configuration
@EnableAutoConfiguration
public class RestaurantsApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestaurantsApplication.class);
	}

}
