package at.lunchinator.voting.jms;

import javax.jms.MapMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Service;

import at.lunchinator.commons.jms.MessageSendingService;
import at.lunchinator.voting.domain.Vote;

/**
 * @author poberbichler
 * @since 01.2015
 */
@Service
class VotingMessageServiceImpl implements MessageSendingService<Vote> {
	private final JmsOperations jmsOperations;

	@Autowired
	public VotingMessageServiceImpl(JmsOperations jmsOperations) {
		this.jmsOperations = jmsOperations;
	}

	@Override
	public void sendMessage(final Vote vote) {
		jmsOperations.send("at.lunchinator.vote", session -> {
			final MapMessage message = session.createMapMessage();
			message.setStringProperty("id", vote.getTarget());
			message.setStringProperty("text", "Hello World from MappedMessage inside VotingLambda");
			return message;
		});
	}
}
