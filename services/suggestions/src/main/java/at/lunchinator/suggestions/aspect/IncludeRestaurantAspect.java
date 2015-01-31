package at.lunchinator.suggestions.aspect;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.lunchinator.suggestions.data.rest.RestaurantRepository;
import at.lunchinator.suggestions.domain.RestaurantDTO;
import at.lunchinator.suggestions.domain.Suggestion;

/**
 * @author poberbichler
 * @since 01.2015
 */
@Aspect
@Component
public class IncludeRestaurantAspect {
	private final RestaurantRepository restaurantRepository;

	@Autowired
	public IncludeRestaurantAspect(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}

	@Pointcut("@annotation(at.lunchinator.suggestions.aspect.IncludeRestaurant)")
	public void methodAnnotated() {
		// empty method
	}

	@AfterReturning(returning = "suggestions", value = "methodAnnotated()")
	public void doMoreStuff(final Collection<Suggestion> suggestions) {
		final Set<String> restaurantIds = suggestions.parallelStream()
				.map(Suggestion::getRestaurant)
				.collect(Collectors.toSet());
		
		final Collection<RestaurantDTO> restaurants = restaurantRepository.findByIds(restaurantIds);
		
		final Map<String, RestaurantDTO> restaurantMap = restaurants.parallelStream()
				.collect(Collectors.toMap(RestaurantDTO::getId, Function.identity()));
		
		suggestions.parallelStream().forEach(suggestion -> 
			suggestion.setFullRestaurant(restaurantMap.get(suggestion.getRestaurant())));
	}
}
