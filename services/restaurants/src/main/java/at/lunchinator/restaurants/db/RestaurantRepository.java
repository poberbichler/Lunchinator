package at.lunchinator.restaurants.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import at.lunchinator.restaurants.domain.Restaurant;

/**
 * @author poberbichler
 * @since 12.2014
 */
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

}
