package me.young1lin.oauth2simple;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author <a href="mailto:young1lin0108@gmail.com">young1lin</a>
 * @since 2021/4/21 下午10:56
 * @version 1.0
 */
public class OAuth2Configuration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeRequests(a -> a
						.antMatchers("/", "/error", "/webjars/**").permitAll()
						.anyRequest().authenticated()
				)
				.exceptionHandling(e -> e
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				)
				.oauth2Login();
		http.logout(l -> l
						.logoutSuccessUrl("/").permitAll()
				);
		http.csrf(c -> c
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				);
	}

}
