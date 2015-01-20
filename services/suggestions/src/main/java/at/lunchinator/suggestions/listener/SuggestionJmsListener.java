package at.lunchinator.suggestions.listener;

import javax.jms.JMSException;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import at.lunchinator.suggestions.service.SuggestionService;

/**
 * @author poberbichler
 * @since 01.2015
 */
@Component
class SuggestionJmsListener {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private SuggestionService suggestionService;

	@Autowired
	public SuggestionJmsListener(SuggestionService suggestionService) {
		this.suggestionService = suggestionService;
	}

	@JmsListener(destination = "at.lunchinator.vote")
	public void handleMessage(final Message message) throws JMSException {
		logger.info("SuggestionJmsListener received message [{}]", message);
		suggestionService.updateVoteCountFor(message.getStringProperty("id"));
	}
}
