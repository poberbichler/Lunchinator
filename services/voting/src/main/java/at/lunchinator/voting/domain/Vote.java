package at.lunchinator.voting.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import at.lunchinator.commons.db.validation.Jsr310Past;

import com.google.common.base.Objects;

/**
 * @author poberbichler
 * @since 01.2015
 */
@Document
@CompoundIndex(unique = true, def = "{target: 1, author: 1}")
public class Vote {
	@Id
	private final String id;

	private final boolean upvote;

	@NotNull
	@Indexed
	private final String target;

	@NotNull
	private String author;

	@NotNull
	@Jsr310Past
	private LocalDateTime createdAt;

	/**
	 * private constructor, needed by jackson
	 */
	private Vote() {
		this.id = null;
		this.upvote = false;
		this.target = null;
		this.author = null;
		this.createdAt = null;
	}

	public Vote(String id, boolean upvote, String target, String author, LocalDateTime createdAt) {
		this.id = id;
		this.upvote = upvote;
		this.target = target;
		this.author = author;
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public boolean isUpvote() {
		return upvote;
	}

	public String getTarget() {
		return target;
	}

	public String getAuthor() {
		return author;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("upvote", upvote).add("target", target).add("author", author)
				.add("createdAt", createdAt).toString();
	}
}
