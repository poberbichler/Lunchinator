package at.lunchinator.web

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import at.lunchinator.domain.Lunch

@RestController
@RequestMapping("api/lunch")
class LunchController {
	private List<Lunch> internalLunches = [
		new Lunch(id: UUID.randomUUID().toString(), name: 'After-Work Bier', restaurant: 'Rinderwahn'),
		new Lunch(id: UUID.randomUUID().toString(), name: 'Mittagspause', restaurant: 'Kantine')
	]

	@GetMapping
	List<Lunch> getLunsches() {
		return internalLunches
	}

	@PostMapping
	List<Lunch> addLunch(@RequestBody Lunch lunch) {
		println "adding $lunch"
		internalLunches << lunch
		return getLunsches()
	}

	@MessageMapping("/add")
	@SendTo("/topic/lunches")
	Lunch receiveLunch(Lunch lunch) {
		println "received $lunch"
		internalLunches << lunch
		return lunch
	}
}
