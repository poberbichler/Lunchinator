package at.lunchinator.restaurants.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.lunchinator.restaurants.db.RestaurantRepository;
import at.lunchinator.restaurants.domain.Restaurant;

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
		
		return Arrays.asList(
				new Restaurant(UUID.randomUUID().toString(), "Hitomi"), 
				new Restaurant(UUID.randomUUID().toString(), "Asia Paradies"));
	}

}
