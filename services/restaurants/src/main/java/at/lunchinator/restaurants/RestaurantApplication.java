package at.lunchinator.restaurants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import at.lunchinator.commons.config.LunchinatorEndpoint;

/**
 * @author poberbichler
 * @since 12.2014
 */
@LunchinatorEndpoint
@SpringBootApplication
public class RestaurantApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestaurantApplication.class);
	}
}
