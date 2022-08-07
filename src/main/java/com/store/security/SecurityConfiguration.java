package com.store.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	   private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JWTFilter filter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	 @Bean
	   @Override
	   public AuthenticationManager authenticationManagerBean() throws
	   Exception {
	      return super.authenticationManagerBean();
	   }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
	      .authorizeRequests()
//	      .antMatchers("/api/v1/permissions/**").hasAnyAuthority("ALL","WRITE_PERMISSION","READ_PERMISSION")
//	      .antMatchers("/api/v1/roles/**").hasAnyRole("USER","ADMIN")
//	      .antMatchers("/api/v1/login", "/api/v1/users/**").permitAll()
	    //  .antMatchers("/api/v1/permissions/**").hasAnyAuthority("WRITE_PERMISSION","READ_PERMISSION")
	      .anyRequest().permitAll()
	      .and()
	      .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
	      .and()
	      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	      .and().formLogin().disable();
	    //  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
			
	}

}
