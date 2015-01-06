package at.lunchinator.suggestions.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import at.lunchinator.commons.db.validation.Jsr310Future;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * @author poberbichler
 * @since 12.2014
 */
@Document
public class Suggestion {
	@Id
	private final String id;

	@NotNull
	private final String suggestedBy;

	@NotNull
	private final LocalDateTime suggestedAt;

	@NotNull
	private final String restaurant;

	@NotNull
	@Jsr310Future
	private LocalDateTime startTime;

	@NotNull
	@Jsr310Future
	private LocalDateTime endTime;

	/**
	 * private default constructor, needed by various frameworks
	 */
	private Suggestion() {
		this.id = null;
		this.suggestedBy = null;
		this.suggestedAt = null;
		this.restaurant = null;
		this.startTime = null;
		this.endTime = null;
	}

	private Suggestion(SuggestionBuilder builder) {
		Preconditions.checkNotNull(builder, "suggestionsbuilder most not be null!");

		this.id = builder.id;
		this.suggestedBy = builder.suggestedBy;
		this.suggestedAt = builder.suggestedAt;
		this.restaurant = builder.restaurant;
		this.startTime = builder.startTime;
		this.endTime = builder.endTime;
	}

	public String getId() {
		return id;
	}

	public String getSuggestedBy() {
		return suggestedBy;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public LocalDateTime getSuggestedAt() {
		return suggestedAt;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", id)
				.add("startTime", startTime)
				.add("endTime", endTime)
				.add("suggestedBy", suggestedBy)
				.add("suggestedAt", suggestedAt)
				.add("restaurant", restaurant)
				.toString();
	}
	
	/**
	 * @author poberbichler
	 * @since 12.2014
	 */
	public static final class SuggestionBuilder {
		private String id;
		private String suggestedBy;
		private String restaurant;
		private LocalDateTime suggestedAt;
		private LocalDateTime startTime;
		private LocalDateTime endTime;

		public SuggestionBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public SuggestionBuilder setSuggestedBy(String suggestedBy) {
			this.suggestedBy = suggestedBy;
			return this;
		}

		public SuggestionBuilder setRestaurant(String restaurant) {
			this.restaurant = restaurant;
			return this;
		}

		public SuggestionBuilder setSuggestedAt(LocalDateTime suggestedAt) {
			this.suggestedAt = suggestedAt;
			return this;
		}

		public SuggestionBuilder setStartTime(LocalDateTime startTime) {
			this.startTime = startTime;
			return this;
		}

		public SuggestionBuilder setEndTime(LocalDateTime endTime) {
			this.endTime = endTime;
			return this;
		}

		public Suggestion build() {
			return new Suggestion(this);
		}
	}
}
