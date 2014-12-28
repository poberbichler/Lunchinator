package at.lunchinator.restaurants;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @author poberbichler
 * @since 12.2014
 */
@PropertySource({ "persistence.properties" })
@SpringBootApplication
public class RestaurantsApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestaurantsApplication.class);
	}
	
	@Bean
	public CorsFilter corsFilter() {
		return new CorsFilter();
	}
}
