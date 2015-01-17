package at.lunchinator.voting.service;

import java.util.Collection;

import at.lunchinator.voting.domain.Vote;

/**
 * @author poberbichler
 * @since 01.2015
 */
public interface VotingService {
	/**
	 * @param target must not be {@code null}
	 * @return a list of {@link Vote votes} for the given target
	 */
	Collection<Vote> findByTarget(String target);

	/**
	 * Saves the given {@link Vote}, and sends a message to all the listening applications
	 * 
	 * @param vote must not be {@code null}
	 * @return the newly saved {@link Vote}
	 */
	Vote save(Vote vote);
}
