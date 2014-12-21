package at.lunchinator.restaurants.service;

import java.util.Collection;

import at.lunchinator.restaurants.domain.Restaurant;

/**
 * @author poberbichler
 * @since 12.2014
 */
public interface RestaurantService {
	/**
	 * @return a collection of every {@link Restaurant} available
	 */
	Collection<Restaurant> findAll();
	
	/**
	 * Saves the given {@link Restaurant}<br>
	 * If it is a new {@link Restaurant}, it will be saved into the db. Otherwise a new one will be created 
	 * 
	 * @param restaurant to be saved
	 * @return the newly saved restaurant
	 */
	Restaurant saveRestaurant(Restaurant restaurant);
}
