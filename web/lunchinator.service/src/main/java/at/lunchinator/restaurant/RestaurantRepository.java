package at.lunchinator.restaurant;

import at.lunchinator.restaurant.Restaurant;

interface RestaurantRepository {
	Iterable<Restaurant> findAll();
}
