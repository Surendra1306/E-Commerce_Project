package in.surendra.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Autowired
	private UserDetailsService detailsService;
	
	@Bean
	public PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
		
		daoAuthProvider.setUserDetailsService(detailsService);
		daoAuthProvider.setPasswordEncoder(pwdEncoder());
		return daoAuthProvider;
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	public SecurityFilterChain secirityFilter(HttpSecurity http) throws Exception{
		return http.csrf()
				   .disable()
				   .authorizeHttpRequests()
				   .requestMatchers("/register","/login","/actuator/**")
				   .permitAll()
				   .and()
				   .build();
	}
	
}
