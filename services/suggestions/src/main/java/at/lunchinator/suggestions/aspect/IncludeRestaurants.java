package at.lunchinator.suggestions.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import at.lunchinator.suggestions.domain.RestaurantDTO;

/**
 * Marker annotation for {@link IncludeRestaurantAspect}<br>
 * If a method is annotated with this annotation the {@link RestaurantDTO} will be set in the result   
 * 
 * @author poberbichler
 * @since 01.2015
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IncludeRestaurants {
	// empty
}
