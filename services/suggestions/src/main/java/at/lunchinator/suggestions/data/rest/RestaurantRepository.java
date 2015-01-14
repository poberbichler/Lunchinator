package at.lunchinator.suggestions.data.rest;

import java.util.Collection;

import at.lunchinator.suggestions.domain.RestaurantDTO;

/**
 * @author poberbichler
 * @since 01.2015
 */
public interface RestaurantRepository {
	/**
	 * @param restaurantId must not be {@code null}
	 * @return the {@link RestaurantDTO} for the given restaurantId (can be {@code null})
	 */
	RestaurantDTO findById(String restaurantId);
	
	/**
	 * @return a {@link Collection} of every {@link RestaurantDTO} available
	 */
	Collection<RestaurantDTO> findAll();
}
