package at.lunchinator.voting.data.db;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import at.lunchinator.voting.domain.Vote;

/**
 * @author poberbichler
 * @since 01.2015
 */
public interface VoteRepository extends MongoRepository<Vote, String> {
	/**
	 * @param target must not be {@code null}
	 * @return a list of {@link Vote votes} for the given parameter
	 */
	Collection<Vote> findByTarget(String target);
}
