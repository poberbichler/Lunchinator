package at.lunchinator.restaurants.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

/**
 * @author patrick
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
}
