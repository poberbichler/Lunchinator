package at.lunchinator.restaurants.service;

import java.util.Collection;

import at.lunchinator.restaurants.domain.Restaurant;

/**
 * @author poberbichler
 * @since 12.2014
 */
public interface RestaurantService {
	Collection<Restaurant> findAll();
}
