package hello;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class UserPasswordAuthProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserPasswordAuthenticationToken token = (UserPasswordAuthenticationToken) authentication;

		if (token.getPrincipal().equals("user") && token.getCredentials().equals("password")) {

			final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new AppAuthority("USER"));
			
			return new UserPasswordAuthenticationToken(authorities, token.getPrincipal().toString(),
					token.getCredentials().toString(), token.getIp());
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		boolean support = false;
		if (authentication.isAssignableFrom(UserPasswordAuthenticationToken.class)) {
			support = true;
		}

		return support;
	}

}
