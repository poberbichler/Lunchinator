package at.lunchinator.suggestions;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * @author poberbichler
 * @since 12.2014
 */
@SpringBootApplication
public class SuggestionsApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(SuggestionsApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SuggestionsApplication.class);
	}
	
	@Bean
	public CorsFilter corsFilter() {
		return new CorsFilter();
	}
}
