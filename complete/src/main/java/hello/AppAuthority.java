package hello;

import org.springframework.security.core.GrantedAuthority;

public class AppAuthority implements GrantedAuthority {

	private final String role;

	public AppAuthority(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return this.role;
	}

}
