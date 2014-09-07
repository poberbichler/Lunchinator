package at.lunchinator.rating;

interface RatingRepository {
	Iterable<Rating> findAll();
}
