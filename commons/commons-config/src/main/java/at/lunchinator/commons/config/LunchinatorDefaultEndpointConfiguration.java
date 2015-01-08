package at.lunchinator.commons.config;

import java.time.LocalDateTime;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import at.lunchinator.commons.db.converter.MongoConverters;
import at.lunchinator.commons.serializer.LocalDateTimeDeserializer;
import at.lunchinator.commons.serializer.LocalDateTimeSerializer;

/**
 * Common beans needed by every LunchinatorEndpoint<br>
 * Would be nice to refactor this class to implement {@link ImportSelector}, so
 * the {@link LunchinatorEndpoint} annotation will be configurable
 * 
 * @author poberbichler
 * @since 01.2015
 */
@Configuration
class LunchinatorDefaultEndpointConfiguration {
	@Bean
	public Jackson2ObjectMapperBuilder jacksonMapper() {
		final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
		builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
		return builder;
	}

	@Bean
	public CustomConversions customConversion() {
		return new CustomConversions(MongoConverters.INSTANCE.getConverters());
	}

	@Bean
	public CorsFilter corsFilter() {
		return new CorsFilter();
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
