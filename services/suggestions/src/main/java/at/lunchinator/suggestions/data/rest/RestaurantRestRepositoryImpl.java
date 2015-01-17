package at.lunchinator.suggestions.data.rest;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import at.lunchinator.suggestions.domain.RestaurantDTO;

import com.google.common.base.Preconditions;

/**
 * Consumer for the REST Api of the Restaurant Endpoint
 * 
 * @author poberbichler
 * @since 01.2015
 */
@Repository
class RestaurantRestRepositoryImpl implements RestaurantRepository {
	private static final RestTemplate restTemplate = new RestTemplate();
	
	private static final String BASE_URL = "http://localhost:8083/restaurants/";
	
	@Override
	public RestaurantDTO findById(final String restaurantId) {
		Preconditions.checkNotNull(restaurantId, "restaurantId must not be null");
		
		return restTemplate.getForObject(BASE_URL + "{restaurantId}", RestaurantDTO.class, restaurantId);
	}
	
	@Override
	public Collection<RestaurantDTO> findByIds(final Collection<String> restaurantIds) {
		Preconditions.checkNotNull(restaurantIds, "restaurantIds must not be null");
		
		final RestaurantDTO[] restaurants = restTemplate.getForObject(BASE_URL + "multiple/{restaurantIds}", 
				RestaurantDTO[].class, restaurantIds);
		
		return Arrays.asList(restaurants);
	}

	@Override
	public Collection<RestaurantDTO> findAll() {
		return Arrays.asList(restTemplate.getForObject(BASE_URL + "all", RestaurantDTO[].class));
	}
}
