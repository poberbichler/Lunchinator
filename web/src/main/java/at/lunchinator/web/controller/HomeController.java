package at.lunchinator.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author poberbichler
 * @since 12.2014
 */
@Controller
@RequestMapping({ "/", "/home" })
public class HomeController {
	@RequestMapping
	public String indexPage() {
		return "index";
	}
}
