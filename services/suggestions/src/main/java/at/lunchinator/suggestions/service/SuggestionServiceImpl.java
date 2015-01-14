package at.lunchinator.suggestions.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import at.lunchinator.suggestions.data.db.SuggestionRepository;
import at.lunchinator.suggestions.data.rest.RestaurantRepository;
import at.lunchinator.suggestions.domain.Suggestion;

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
		List<Suggestion> suggestions = suggestionRepository.findAll();
		return suggestions;
	}

	@Override
	public Suggestion save(final Suggestion suggestion) {
		Preconditions.checkNotNull(suggestion, "suggestion must not be null!");
		
		suggestion.setSuggestedAt(LocalDateTime.now());
		suggestionRepository.save(suggestion);
		
		return suggestion;
	}

}
