package at.lunchinator.suggestions.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.lunchinator.suggestions.aspect.IncludeRestaurants;
import at.lunchinator.suggestions.data.db.SuggestionRepository;
import at.lunchinator.suggestions.domain.Suggestion;

import com.google.common.base.Preconditions;

/**
 * @author poberbichler
 * @since 12.2014
 */
@Service
class SuggestionServiceImpl implements SuggestionService {
	private final SuggestionRepository suggestionRepository;

	@Autowired
	public SuggestionServiceImpl(SuggestionRepository suggestionRepository) {
		this.suggestionRepository = suggestionRepository;
	}

	@Override
	@IncludeRestaurants
	public Collection<Suggestion> findAll() {
		return suggestionRepository.findAll();
	}
	
	@Override
	@IncludeRestaurants
	public Collection<Suggestion> findUpcoming() {
		return suggestionRepository.findAll().parallelStream()
			.filter(suggestion -> suggestion.isUpcoming())
			.collect(Collectors.toList());
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
