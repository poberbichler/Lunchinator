package at.lunchinator.restaurants.endpoint;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping("all")
	public Collection<Restaurant> findAll() {
		return restaurantService.findAll();
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Restaurant saveRestaurant(@RequestBody Restaurant restaurant) {
		return restaurantService.saveRestaurant(restaurant);
	}
	
	@RequestMapping("{restaurantId}")
	public Restaurant findById(@PathVariable("restaurantId") String restaurantId) {
		return restaurantService.findById(restaurantId);
	}
	
	@RequestMapping(value = "/string/{restaurantId}")
	public String findStringById(@PathVariable("restaurantId") String restaurantId) {
		return restaurantService.findById(restaurantId).toString();
	}
}
