package at.lunchinator.restaurants.endpoint;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * {@link Filter ServletFilter} to enable CORS requests by adding
 * {@code "Access-Control-Allow-Origin: *"} to the response headers
 * 
 * @author poberbichler
 * @since 12.2014
 */
@Component
public class CORSFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		final HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, x-xsrf-token");
		
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// empty
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
		// empty
	}
}
