package at.lunchinator.voting.endpoint;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import at.lunchinator.voting.domain.Vote;
import at.lunchinator.voting.service.VotingService;

/**
 * @author patrick
 * @since 01.2015
 */
@RestController
@RequestMapping("voting")
public class VotingEndpoint {
	private final VotingService votingService;

	@Autowired
	public VotingEndpoint(VotingService votingService) {
		this.votingService = votingService;
	}

	@RequestMapping("{targetId}")
	public Collection<Vote> findByTargetId(@PathVariable("targetId") String targetId) {
		return votingService.findByTarget(targetId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Vote voteFor(@RequestBody Vote vote, @RequestHeader("Authorization") String userId) {
		vote.setAuthor(userId);
		return votingService.save(vote);
	}
}
