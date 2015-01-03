package at.lunchinator.suggestions;

import java.time.LocalDateTime;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import at.lunchinator.commons.db.converter.MongoConverters;
import at.lunchinator.commons.serializer.LocalDateTimeDeserializer;

/**
 * @author poberbichler
 * @since 12.2014
 */
@SpringBootApplication
public class SuggestionsApplication extends SpringBootServletInitializer {
	@Autowired
	private AutowireCapableBeanFactory beanFactory;
	
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

	@Bean
	public CustomConversions customConversion() {
		return new CustomConversions(MongoConverters.INSTANCE.getConverters());
	}

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
		return new ValidatingMongoEventListener(validator());
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}
}
