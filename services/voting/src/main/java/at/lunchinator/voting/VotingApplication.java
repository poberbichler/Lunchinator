package at.lunchinator.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import at.lunchinator.commons.config.LunchinatorEndpoint;

/**
 * @author poberbichler
 * @since 01.2015
 */
@LunchinatorEndpoint
@SpringBootApplication
public class VotingApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(VotingApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(VotingApplication.class);
	}
}
