package at.lunchinator.suggestions.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import at.lunchinator.suggestions.db.SuggestionRepository;
import at.lunchinator.suggestions.domain.Suggestion;

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
	public Collection<Suggestion> findAll() {
		return suggestionRepository.findAll();
	}

	@Override
	public Suggestion save(final Suggestion suggestion) {
		Preconditions.checkNotNull(suggestion, "suggestion must not be null!");
		
		return null;
	}

}
