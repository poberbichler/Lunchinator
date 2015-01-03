package at.lunchinator.commons.db.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

/**
 * Utility class to keep every available {@link Converter} for {@code MongoDB}
 * together
 * 
 * @author poberbichler
 * @since 01.2015
 */
public enum MongoConverters {
	INSTANCE;

	private final List<Converter<?, ?>> converters;

	private MongoConverters() {
		converters = new ArrayList<>(1);
		converters.add(new MongoLocalDateTimeConverter());
	}

	/**
	 * @return {@link Collection} of {@link Converter}
	 */
	public List<Converter<?, ?>> getConverters() {
		return Collections.unmodifiableList(converters);
	}
}
