package at.lunchinator.commons.error;

import javax.validation.ConstraintViolation;

import com.google.common.base.Objects;

/**
 * Immutable wrapper for a single error message. Contains the field containing 
 * the error, and the corresponding message.
 * 
 * @author poberbichler
 * @since 01.2014
 */
public final class ErrorContainer {
	private final String field;
	private final String message;

	/**
	 * Takes the given {@link ConstraintViolation}, and reads the needed properties 
	 * from it
	 */
	public ErrorContainer(final ConstraintViolation<?> violation) {
		this.field = violation.getPropertyPath().toString();
		this.message = violation.getMessage();
	}

	/**
	 * Default constructor, in case it is neeed without a {@link ConstraintViolation} 
	 */
	public ErrorContainer(final String field, final String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("field", field)
				.add("message", message)
				.toString();
	}
}
