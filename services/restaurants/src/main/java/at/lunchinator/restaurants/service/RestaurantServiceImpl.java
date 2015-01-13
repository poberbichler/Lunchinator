package at.lunchinator.restaurants.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.lunchinator.restaurants.db.RestaurantRepository;
import at.lunchinator.restaurants.domain.Restaurant;

import com.google.common.base.Preconditions;

/**
 * @author poberbichler
 * @since 12.2014
 */
@Component
class RestaurantServiceImpl implements RestaurantService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final RestaurantRepository restaurantRepository;

	@Autowired
	public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public Collection<Restaurant> findAll() {
		logger.debug("find all restaurnts called {}", restaurantRepository.getClass());
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant saveRestaurant(final Restaurant restaurant) {
		Preconditions.checkNotNull(restaurant, "restaurant must not be mull");
		
		logger.debug("saving restaurant [{}]", restaurant);
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant findById(final String restaurantId) {
		Preconditions.checkNotNull(restaurantId, "restaurantId must not be null");
		return restaurantRepository.findOne(restaurantId);
	}
}
