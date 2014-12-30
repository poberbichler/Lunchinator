package at.lunchinator.suggestions.formatter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

/**
 * TODO: move to commons
 * 
 * @author poberbichler
 * @since 12.2014
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
	@Override
	public LocalDateTime convert(final String input) {
		return LocalDateTime.parse(input.substring(0, input.length() - 2));
	}
	
	@Override
	public JavaType getInputType(TypeFactory arg0) {
		return arg0.uncheckedSimpleType(String.class);
	}

	@Override
	public JavaType getOutputType(TypeFactory arg0) {
		return arg0.uncheckedSimpleType(LocalDateTime.class);
	}
}
