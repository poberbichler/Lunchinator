package at.lunchinator.commons.config;

import java.time.LocalDateTime;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import at.lunchinator.commons.db.converter.MongoConverters;
import at.lunchinator.commons.error.LunchinatorExceptionHandler;
import at.lunchinator.commons.serialization.LocalDateTimeDeserializer;
import at.lunchinator.commons.serialization.LocalDateTimeSerializer;

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
	private static final String BUNDLE_BASE_NAME = "messages";
	private static final String ALLOWED_HTTP_HEADERS = CorsFilter.DEFAULT_ALLOWED_HTTP_HEADERS + ",Authorization";
	
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
	public FilterRegistrationBean filterRegistration() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CorsFilter());
		registrationBean.addInitParameter(CorsFilter.PARAM_CORS_ALLOWED_HEADERS, ALLOWED_HTTP_HEADERS);

		return registrationBean;
	}

	@Bean
	public ValidatingMongoEventListener validatingMongoEventListener() {
		return new ValidatingMongoEventListener(validator());
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public LunchinatorExceptionHandler exceptionHandler() {
		return new LunchinatorExceptionHandler(messageSource());
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		final ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames(BUNDLE_BASE_NAME);
		
		return source;
	}
}
