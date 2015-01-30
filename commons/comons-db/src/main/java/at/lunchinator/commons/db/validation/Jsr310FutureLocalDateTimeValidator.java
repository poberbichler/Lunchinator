package at.lunchinator.commons.db.validation;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for Jsr310 date/time api validation<br>
 * <strong>NOTE:</strong> can be removed when hibernate validator 5.2.x is
 * available
 * 
 * @author poberbichler
 * @since 01.2015
 */
public class Jsr310FutureLocalDateTimeValidator implements ConstraintValidator<Jsr310Past, LocalDateTime> {

	@Override
	public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		
		return value.isBefore(LocalDateTime.now());
	}

	@Override
	public void initialize(Jsr310Past constraintAnnotation) {
		// empty
	}
}
