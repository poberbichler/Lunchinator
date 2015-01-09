package at.lunchinator.suggestions.endpoint;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import at.lunchinator.suggestions.domain.Suggestion;
import at.lunchinator.suggestions.service.SuggestionService;

/**
 * @author poberbichler
 * @since 12.2014
 */
@RestController
@RequestMapping("suggestions")
public class SuggestionEndpoint {
	private final SuggestionService suggestionService;

	@Autowired
	public SuggestionEndpoint(SuggestionService suggestionService) {
		this.suggestionService = suggestionService;
	}

	@RequestMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Suggestion> findAll() {
		return suggestionService.findAll();
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Suggestion save(@RequestBody Suggestion suggestion, @RequestHeader("Authorization") String userId) {
		suggestion.setSuggestedBy(userId);
		return suggestionService.save(suggestion);
	}
}
