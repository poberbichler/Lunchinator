package at.lunchinator.restaurant;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryMock implements RestaurantRepository {
	@Override
	public Iterable<Restaurant> findAll() {
		return Arrays.asList(createRestaurant("Natsu Sushi"), createRestaurant("Asia Paradies"));
	}

	private Restaurant createRestaurant(final String name) {
		Restaurant result = new Restaurant();
		
		result.setId(UUID.randomUUID());
		result.setName(name);
		
		return result;
	}
}
