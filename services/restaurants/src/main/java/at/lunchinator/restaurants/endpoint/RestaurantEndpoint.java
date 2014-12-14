package at.lunchinator.restaurants.endpoint;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.lunchinator.restaurants.domain.Restaurant;
import at.lunchinator.restaurants.service.RestaurantService;

/**
 * @author poberbichler
 * @since 12.2014
 */
@RestController
@RequestMapping("restaurants")
public class RestaurantEndpoint {
	private final RestaurantService restaurantService;

	@Autowired
	public RestaurantEndpoint(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@RequestMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Restaurant> findAll() {
		return restaurantService.findAll();
	}
}
