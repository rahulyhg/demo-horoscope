package in.nareshsharma.horo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${horo.user}")
	private String user;
	@Value("${horo.pass}")
	private String pass;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		String encPpassword = passwordEncoder().encode(pass);
		auth.inMemoryAuthentication().withUser(user)
				.password(encPpassword).roles("USER");
	}

	  @Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/login-page/**", "/api/**", "/h2-console/**");
	  }

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().anyRequest().hasRole("USER")
			.and()
				.formLogin()
					.loginPage("/login")
					.failureUrl("/login?error=1").loginProcessingUrl("/login")
					.permitAll()
			.and()
				.logout()
					.logoutSuccessUrl("/login");
		http.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
  
}