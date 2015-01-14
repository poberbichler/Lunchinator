package at.lunchinator.suggestions.data.rest;

import java.util.Collection;
import java.util.Collections;

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
	
	@Override
	public RestaurantDTO findById(final String restaurantId) {
		Preconditions.checkNotNull(restaurantId, "restaurantId must not be null");
		
		RestaurantDTO restaurant = restTemplate.getForObject("http://localhost:8083/restaurants/{restaurantId}", RestaurantDTO.class, restaurantId);
		return restaurant;
	}

	@Override
	public Collection<RestaurantDTO> findAll() {
		return Collections.emptyList();
	}
}
