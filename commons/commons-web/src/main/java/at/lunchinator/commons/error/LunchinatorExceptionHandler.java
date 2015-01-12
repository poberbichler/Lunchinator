package at.lunchinator.commons.error;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
	private final MessageSource messageSource;

	public LunchinatorExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ ConstraintViolationException.class })
	public Collection<ErrorContainer> handleException(final ConstraintViolationException caughtException) {
		final Collection<ErrorContainer> result = new ArrayList<>(caughtException.getConstraintViolations().size());
		final Locale locale = LocaleContextHolder.getLocale();
		
		for (final ConstraintViolation<?> violation : caughtException.getConstraintViolations()) {
			final String field = messageSource.getMessage(convertToProperty(violation), null, locale);
			final String message = messageSource.getMessage(violation.getMessageTemplate(), null, locale);
			
			result.add(new ErrorContainer(field, message));
		}

		return result;
	}
	
	private String convertToProperty(final ConstraintViolation<?> violation) {
		final StringBuilder builder = new StringBuilder();
		builder.append(violation.getRootBeanClass().getSimpleName()).append(".")
			.append(violation.getPropertyPath().toString());
		
		return builder.toString();
	}
}
