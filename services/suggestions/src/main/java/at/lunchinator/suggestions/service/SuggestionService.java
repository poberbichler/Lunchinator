package at.lunchinator.suggestions.service;

import java.util.Collection;

import at.lunchinator.suggestions.domain.Suggestion;

/**
 * @author patrick
 * @since 12.2014
 */
public interface SuggestionService {
	/**
	 * @return a {@link Collection} of every {@link Suggestion} available.
	 */
	Collection<Suggestion> findAll();
	
	/**
	 * Saves the given suggestion<br>
	 * If it is a new {@link Suggestion}, a new one will be created and saved in the db. Otherwise the existing {@link Suggestion} will be updated
	 * 
	 * @param suggestion to be saved
	 * @return the newly added suggestion, including it's id
	 */
	Suggestion save(Suggestion suggestion);
}
