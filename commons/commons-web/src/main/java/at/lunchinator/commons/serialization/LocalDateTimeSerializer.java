package at.lunchinator.commons.serialization;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Serializes the given {@link LocalDateTime} into a timestamp readable by javascript, with the UTC timezone
 * 
 * @author poberbichler
 * @since 01.2015
 */
public final class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
	@Override
	public void serialize(LocalDateTime input, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		generator.writeNumber(input.toInstant(ZoneOffset.UTC).toEpochMilli());
	}
}
