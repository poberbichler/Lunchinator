package at.lunchinator.commons.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.mapping.Document;

import at.lunchinator.commons.db.converter.MongoConverters;

/**
 * Convenience annotation for the lunchinator application<br>
 * Provides the basic beans for validation, CORS and {@link MongoConverters}
 * 
 * @author poberbichler
 * @since 01.2015
 */
@Document
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ LunchinatorDefaultEndpointConfiguration.class })
public @interface LunchinatorEndpoint {

}
