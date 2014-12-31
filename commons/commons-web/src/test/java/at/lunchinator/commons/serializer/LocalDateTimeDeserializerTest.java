package at.lunchinator.commons.serializer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonParser;

/**
 * @author poberbichler
 * @since 12.2014
 */
@RunWith(Parameterized.class)
public class LocalDateTimeDeserializerTest {
	private static final LocalDateTimeDeserializer deserializer = new LocalDateTimeDeserializer();

	private final String value;
	private final LocalDateTime expectedValue;
	
	@Mock
	private JsonParser parser;

	public LocalDateTimeDeserializerTest(final String inputValue, final LocalDateTime expectedValue) {
		this.value = inputValue;
		this.expectedValue = expectedValue;
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldDeserializeJavascriptDates() throws IOException {
		when(parser.getValueAsString()).thenReturn(value);
		assertEquals(expectedValue, deserializer.deserialize(parser, null));
	}

	@Parameters
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] {
			{"2014-12-30T19:30:38.636Z", LocalDateTime.parse("2014-12-30T19:30:38.63")}, 
			{"1989-05-19T14:12:00.000Z", LocalDateTime.parse("1989-05-19T14:12:00.00")}
		});
	}
}
