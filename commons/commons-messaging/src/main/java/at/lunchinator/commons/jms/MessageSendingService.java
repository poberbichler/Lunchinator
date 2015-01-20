package at.lunchinator.commons.jms;

/**
 * Global interface for the single JMS sending services
 * 
 * @author poberbichler
 * @since 01.2015
 */
public interface MessageSendingService<T> {
	/**
	 * Sends the given message
	 * @param message must not be {@code null}
	 */
	void sendMessage(T message);
}
