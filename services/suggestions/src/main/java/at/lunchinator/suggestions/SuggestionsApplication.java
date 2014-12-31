package at.lunchinator.suggestions;

import java.time.LocalDateTime;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import at.lunchinator.commons.serializer.LocalDateTimeDeserializer;

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
	public Jackson2ObjectMapperBuilder jacksonMapper() {
		final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
		return builder;
	}
	
	@Bean
	public CorsFilter corsFilter() {
		return new CorsFilter();
	}
}
