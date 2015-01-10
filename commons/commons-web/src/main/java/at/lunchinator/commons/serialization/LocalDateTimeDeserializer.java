package at.lunchinator.commons.serialization;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Kind of ugly {@link JsonDeserializer} for {@link LocalDateTime}, directly from the string sent by javascript<br>
 * Trims the end of the input {@link String}, and uses the trimmed {@link String} to parse it into a {@link LocalDateTime}
 * 
 * @author poberbichler
 * @since 12.2014
 */
public final class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		final String input = parser.getValueAsString();
		return LocalDateTime.parse(input.substring(0, input.length() - 2));
	}
}
