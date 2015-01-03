package at.lunchinator.commons.db.converter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mongodb.DBObject;

/**
 * @author poberbichler
 * @since 01.2015
 */
@RunWith(Parameterized.class)
public class MongoLocalDateTimeConverterTest {
	private static final MongoLocalDateTimeConverter converter = new MongoLocalDateTimeConverter();

	@Mock private DBObject source;
	@Mock private DBObject dateSource;
	@Mock private DBObject timeSource;
	
	private final LocalDateTime expcetedValue;

	public MongoLocalDateTimeConverterTest(final LocalDateTime expcetedValue) {
		MockitoAnnotations.initMocks(this);
		this.expcetedValue = expcetedValue.withSecond(0).withNano(0);

		when(source.get("date")).thenReturn(dateSource);
		when(source.get("time")).thenReturn(timeSource);
		
		when(dateSource.get("year")).thenReturn(expcetedValue.getYear());
		when(dateSource.get("month")).thenReturn(expcetedValue.getMonthValue());
		when(dateSource.get("day")).thenReturn(expcetedValue.getDayOfMonth());
		
		when(timeSource.get("hour")).thenReturn(expcetedValue.getHour());
		when(timeSource.get("minute")).thenReturn(expcetedValue.getMinute());
	}
	
	@Test
	public void shouldConvertToRightDate() {
		assertEquals(expcetedValue, converter.convert(source));
	}
	
	@Parameters
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][] { 
				{LocalDateTime.parse("2014-04-12T15:15")}, 
				{LocalDateTime.parse("1989-05-19T14:13")},
				{LocalDateTime.now()}
		});
	}
}
