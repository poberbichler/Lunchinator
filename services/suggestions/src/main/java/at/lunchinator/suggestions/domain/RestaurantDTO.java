package at.lunchinator.suggestions.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * Minimal representation of an restaurant
 * 
 * @author poberbichler
 * @since 01.2015
 */
public class RestaurantDTO {
	private final String id;
	private final String name;

	@JsonCreator
	public RestaurantDTO(@JsonProperty("id") String id, @JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("name", name).toString();
	}
}
