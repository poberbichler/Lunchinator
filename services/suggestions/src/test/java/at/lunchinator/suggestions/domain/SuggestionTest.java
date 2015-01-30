package at.lunchinator.suggestions.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

/**
 * @author poberbichler
 * @since 01.2015
 */
public class SuggestionTest {
	@Test
	public void shouldBeUpcomingIfBothDatesAreInFuture() {
		final LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
		assertTrue(createWith(futureDate, futureDate).isUpcoming());
	}

	@Test
	public void shouldNotBeUpcomingIfBothTimesAreInPast() {
		final LocalDateTime futureDate = LocalDateTime.now().minusDays(1);
		assertFalse(createWith(futureDate, futureDate).isUpcoming());
	}
	
	@Test
	public void shouldNotBeUpcomingIfOneIsInThePast() {
		assertFalse(createWith(LocalDateTime.now().plusDays(1), LocalDateTime.now().minusDays(2)).isUpcoming());
	}
	
	/**
	 * @return a {@link Suggestion} with the given starttime end endtime
	 */
	private Suggestion createWith(LocalDateTime startTime, LocalDateTime endTime) {
		return new Suggestion.SuggestionBuilder().setStartTime(startTime).setEndTime(endTime).build();
	}
}
