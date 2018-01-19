package hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class UserPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	protected UserPasswordAuthenticationFilter() {
		super("/userpassword");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		UserPasswordAuthenticationToken token = null;

		final String user = request.getParameter("user");
		final String password = request.getParameter("password");
		String ip = request.getHeader("X-FORWARDED-FOR");

		if (ip == null) {
			ip = request.getRemoteAddr();
		}

		if (user != null && password != null) {
			token = new UserPasswordAuthenticationToken(user, password, ip);
		}

		return this.getAuthenticationManager().authenticate(token);
	}

}
