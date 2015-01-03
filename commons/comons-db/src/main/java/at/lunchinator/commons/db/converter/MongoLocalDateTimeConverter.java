package at.lunchinator.commons.db.converter;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

/**
 * Converter to transform saved {@link LocalDateTime} from {@link DBObject} back
 * to {@link LocalDateTime}<br>
 * <strong>NOTE:</strong> This class can be removed when spring-data is updated
 * to the 1.7 version
 * <p>
 * 
 * The mongo internal json looks like this:
 * 
 * <pre>
 *  date format: { "year" : 2014 , "month" : 12 , "day" : 10}
 *  time format: { "hour" : 9 , "minute" : 10 , "second" : 0 , "nano" : 0}
 * 
 * @author poberbichler
 * @since 01.2015
 */
public final class MongoLocalDateTimeConverter implements Converter<DBObject, LocalDateTime> {
	@Override
	public LocalDateTime convert(final DBObject source) {
		DBObject date = (DBObject) source.get("date");
		DBObject time = (DBObject) source.get("time");
		return LocalDateTime.of(intValue(date, "year"), intValue(date, "month"), intValue(date, "day"), intValue(time, "hour"),
				intValue(time, "minute"));
	}

	/**
	 * @return a casted {@code int} from the given {@link DBObject} with the
	 *         given key
	 */
	private int intValue(final DBObject object, final String key) {
		return (int) object.get(key);
	}
}
