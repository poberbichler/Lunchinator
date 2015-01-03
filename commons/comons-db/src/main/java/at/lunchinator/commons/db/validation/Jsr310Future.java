package at.lunchinator.commons.db.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom annotation, because {@link javax.validation.constraints.Future} is not
 * yet supported with the JSR310 date/time api<br>
 * <strong>NOTE:</strong> can be removed when hibernate validator 5.2.x is
 * available
 * 
 * @author poberbichler
 * @since 01.2015
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = { Jsr310FutureLocalDateTimeValidator.class })
public @interface Jsr310Future {
	String message() default "{javax.validation.constraints.Future.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
