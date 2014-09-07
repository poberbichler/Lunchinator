package at.lunchinator.voting;

interface VotingRepository {
	Iterable<Voting> findAll();
}
