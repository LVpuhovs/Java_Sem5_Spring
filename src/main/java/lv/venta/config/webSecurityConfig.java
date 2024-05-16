package lv.venta.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class webSecurityConfig {
	
	@Bean
	public UserDetailsService testUsers() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails u1Details = 
				User
				.builder()
				.username("admin")
				.password(encoder.encode("123456"))
				.authorities("ADMIN")
				.build();
		
		UserDetails u2Details = 
				User
				.builder()
				.username("zigis")
				.password(encoder.encode("4202024"))
				.authorities("USER")
				.build();
		
		
		UserDetails u3Details = 
				User
				.builder()
				.username("ivo")
				.password(encoder.encode("098765"))
				.authorities("USER", "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(u1Details, u2Details, u3Details);
	}
	@Bean
	public SecurityFilterChain configureEndpoints(HttpSecurity http) throws Exception {
		
			http
			.authorizeHttpRequests(auth -> auth
			.requestMatchers("/hello").permitAll()
			.requestMatchers("/hello/msg").permitAll()
			.requestMatchers("/product/test").hasAuthority("ADMIN")
			.requestMatchers("/product/all").permitAll()
			.requestMatchers("/product/one**").permitAll()
			.requestMatchers("/product/all/**").permitAll()
			.requestMatchers("/product/insert").hasAuthority("ADMIN")
			.requestMatchers("/product/update**").hasAuthority("ADMIN")
			.requestMatchers("/product/delete**").hasAuthority("ADMIN")
			.requestMatchers("/product/info/filter/**").hasAuthority("USER")
			.requestMatchers("/product/info/total").hasAuthority("ADMIN")
			);
			http.formLogin(form -> form.permitAll());
			
		return http.build();
	}
}
