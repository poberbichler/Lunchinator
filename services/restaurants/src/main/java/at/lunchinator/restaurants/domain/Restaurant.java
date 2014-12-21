package at.lunchinator.restaurants.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.google.common.base.Objects;

/**
 * @author poberbichler
 * @since 12.2014
 */
public class Restaurant {
	@Id
	private String id;

	@NotNull
	private String name;
	
	public Restaurant() {
		
	}
	
	public Restaurant(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.toString();
	}
	
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		
		if (!(that instanceof Restaurant)) {
			return false;
		}
		
		final Restaurant thatRestaurant = (Restaurant) that;
		return Objects.equal(this.id, thatRestaurant.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id);
	}
}
