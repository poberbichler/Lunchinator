package at.lunchinator.suggestions.data.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import at.lunchinator.suggestions.domain.Suggestion;

/**
 * @author poberbichler
 * @since 12.2014
 */
public interface SuggestionRepository extends MongoRepository<Suggestion, String> {

}
