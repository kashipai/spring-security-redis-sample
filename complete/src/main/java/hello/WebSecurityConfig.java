package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		UserPasswordAuthenticationFilter filter = new UserPasswordAuthenticationFilter();
		filter.setAuthenticationSuccessHandler(new UserPasswordSuccessHandler());

		List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
		providers.add(new UserPasswordAuthProvider());

		filter.setAuthenticationManager(new ProviderManager(providers));

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		http.authorizeRequests().antMatchers("/", "/home").hasAnyAuthority("USER");
		http.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
		http.authorizeRequests().antMatchers("/userpassword").permitAll();
	}

	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	// }

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.debug(true);
	}
}
