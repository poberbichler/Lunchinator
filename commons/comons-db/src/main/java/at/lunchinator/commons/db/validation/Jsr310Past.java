package at.lunchinator.commons.db.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Custom annotation, because {@link javax.validation.constraints.Past} is not
 * yet supported with the JSR310 date/time api<br>
 * <strong>NOTE:</strong> can be removed when hibernate validator 5.2.x is
 * available
 * 
 * @author poberbichler
 * @since 01.2015
 */
@Document
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = Jsr310FutureLocalDateTimeValidaotr.class)
public @interface Jsr310Past {
	String message() default "{javax.validation.constraints.Past.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
