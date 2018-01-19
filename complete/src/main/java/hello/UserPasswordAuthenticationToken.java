package hello;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserPasswordAuthenticationToken extends AbstractAuthenticationToken {

	private transient final String user;
	private transient final String password;
	private transient final String ipAddress;

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

	public String getIp() {
		return this.ipAddress;
	}

	public UserPasswordAuthenticationToken(final Collection<? extends GrantedAuthority> authorities, final String user,
			final String password, final String ipAddress) {
		super(authorities);
		this.user = user;
		this.password = password;
		this.ipAddress = ipAddress;
	}

	public UserPasswordAuthenticationToken(final String user, final String password, final String ipAddress) {
		super(null);
		this.user = user;
		this.password = password;
		this.ipAddress = ipAddress;
	}

	@Override
	public Object getCredentials() {
		return this.password;
	}

	@Override
	public Object getPrincipal() {
		return this.user;
	}

}
