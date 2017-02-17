package at.lunchinator

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

@SpringBootApplication
@EnableWebSocketMessageBroker
class Lunchinator extends AbstractWebSocketMessageBrokerConfigurer {
	static void main(String[] args) {
		SpringApplication.run(Lunchinator.class, args);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/lunchinator").withSockJS()
	}

	@Override
	void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic")
		config.setApplicationDestinationPrefixes("/app")
	}
}
