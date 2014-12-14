package at.lunchinator.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author poberbichler
 * @since 12.2014
 */
@Component
@RequestMapping("templates")
public class TemplateController {
	@RequestMapping("stuff")
	public String stuffPage() {
		return "partials/restaurants";
	}
	
	@RequestMapping("home")
	public String homePage() {
		return "partials/home";
	}
}
