package at.lunchinator.voting.service;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.lunchinator.commons.jms.MessageSendingService;
import at.lunchinator.voting.data.db.VoteRepository;
import at.lunchinator.voting.domain.Vote;

import com.google.common.base.Preconditions;

/**
 * @author poberbichler
 * @since 01.2015
 */
@Service
class VotingServiceImpl implements VotingService {
	private final VoteRepository voteRepository;
	private final MessageSendingService<Vote> messageSendingService;

	@Autowired
	public VotingServiceImpl(VoteRepository voteRepository, MessageSendingService<Vote> messageSendingService) {
		this.voteRepository = voteRepository;
		this.messageSendingService = messageSendingService;
	}

	@Override
	public Collection<Vote> findByTarget(final String target) {
		Preconditions.checkNotNull(target, "id of the target must not be null");
		return voteRepository.findByTarget(target);
	}

	@Override
	public Vote save(final Vote vote) {
		Preconditions.checkNotNull(vote, "vote must not be null");
		
		vote.setCreatedAt(LocalDateTime.now());
		voteRepository.save(vote);
		
		messageSendingService.sendMessage(vote);
		
		return vote;
	}

}