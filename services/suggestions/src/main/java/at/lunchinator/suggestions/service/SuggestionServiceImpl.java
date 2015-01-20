package at.lunchinator.suggestions.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.lunchinator.suggestions.data.db.SuggestionRepository;
import at.lunchinator.suggestions.data.rest.RestaurantRepository;
import at.lunchinator.suggestions.domain.RestaurantDTO;
import at.lunchinator.suggestions.domain.Suggestion;

import com.google.common.base.Preconditions;

/**
 * @author poberbichler
 * @since 12.2014
 */
@Service
class SuggestionServiceImpl implements SuggestionService {
	private final SuggestionRepository suggestionRepository;
	private final RestaurantRepository restaurantRepository;

	@Autowired
	public SuggestionServiceImpl(SuggestionRepository suggestionRepository, RestaurantRepository restaurantRepository) {
		this.suggestionRepository = suggestionRepository;
		this.restaurantRepository = restaurantRepository;
	}

	@Override
	public Collection<Suggestion> findAll() {
		final Collection<Suggestion> suggestions = suggestionRepository.findAll();
		final Collection<String> restaurantSet = suggestions.parallelStream()
				.map(Suggestion::getRestaurant)
				.collect(Collectors.toSet());
		
		final Collection<RestaurantDTO> restaurants = restaurantRepository.findByIds(restaurantSet);
		final Map<String, RestaurantDTO> restaurantMap = restaurants.parallelStream()
				.collect(Collectors.toMap(RestaurantDTO::getId, Function.identity()));
		
		suggestions.parallelStream().forEach(suggestion -> 
				suggestion.setFullRestaurant(restaurantMap.get(suggestion.getRestaurant())));
		
		return suggestions;
	}

	@Override
	public Suggestion save(final Suggestion suggestion) {
		Preconditions.checkNotNull(suggestion, "suggestion must not be null!");
		
		suggestion.setSuggestedAt(LocalDateTime.now());
		suggestionRepository.save(suggestion);
		
		return suggestion;
	}

	@Override
	public Suggestion updateVoteCountFor(final String suggestionId) {
		Preconditions.checkNotNull(suggestionId, "suggestionId must not be null");
		
		final Suggestion suggestion = suggestionRepository.findOne(suggestionId);
		if (suggestion == null) {
			throw new IllegalArgumentException(String.format("Suggestion with the id [%s] not found", suggestionId));
		}
		
		suggestion.addToTotalVote();
		suggestionRepository.save(suggestion);
		
		return suggestion;
	}

}
