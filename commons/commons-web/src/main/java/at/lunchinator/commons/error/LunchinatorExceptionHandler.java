package at.lunchinator.commons.error;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author poberbichler
 * @since 01.2015
 */
@ControllerAdvice
public class LunchinatorExceptionHandler {
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ConstraintViolationException.class})
	public Collection<ErrorContainer> handleException(final ConstraintViolationException caughtException) {
		final Collection<ErrorContainer> result = new ArrayList<>(caughtException.getConstraintViolations().size());
		
		for (final ConstraintViolation<?> violation : caughtException.getConstraintViolations()) {
			result.add(new ErrorContainer(violation));
		}
		
		return result;
	}
}
