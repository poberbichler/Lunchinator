package at.lunchinator.suggestions.service;

import java.util.Collection;

import at.lunchinator.suggestions.domain.RestaurantDTO;
import at.lunchinator.suggestions.domain.Suggestion;

/**
 * @author poberbichler
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
	 * @return the newly added suggestion, including it's id and the {@link RestaurantDTO}
	 */
	Suggestion save(Suggestion suggestion);
	
	/**
	 * Searches for the {@link Suggestion} with the given id, and updates the totalVoteCount
	 * 
	 * @param suggestionId (must not be {@code null}
	 * @return the updated {@link Suggestion}
	 * @throws IllegalArgumentException in case the suggestion is not found
	 */
	Suggestion updateVoteCountFor(String suggestionId);
	
	/**
	 * @return a {@link Collection} of every {@link Suggestion} with a future from and to date
	 */
	Collection<Suggestion> findUpcoming();
}
