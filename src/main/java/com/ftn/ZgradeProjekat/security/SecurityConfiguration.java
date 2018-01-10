package com.ftn.ZgradeProjekat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication( AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean()
			throws Exception {
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter
				.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
				/*
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				*/
				.cors().and()
			.authorizeRequests()
				.antMatchers("/index.html", "/api/login").permitAll()
				//.antMatchers("/api/**").permitAll() //only administrator can add and edit data
				.antMatchers("/test/hhh").hasAuthority("ROLE_ADMIN")
				.antMatchers("/building/add").hasAuthority("ROLE_ADMIN")
				.antMatchers("/building/deleteBuilding/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/building/getAllBuildings").hasAuthority("ROLE_ADMIN")
				.antMatchers("/building/findByBuildingId/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/building/addLocationToBuilding").hasAuthority("ROLE_ADMIN")
				.antMatchers("/location/deleteLocation/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/location/findByLocationId/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/user/getAllTenants").hasAuthority("ROLE_ADMIN")
				.antMatchers("/location/connectTenantAndApartment/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/location/user/getAllInstitution").hasAuthority("ROLE_ADMIN")
				.antMatchers("/building/addResponsiblePerson/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/building/getAllResponsiblePersons/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/building/deleteResponsiblePerson/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/user/getAllTenantsFromBuilding/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/building/setBuildingManager/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/building/getAllResponsiblePersonsByLocationId/**").hasAnyAuthority("ROLE_ADMIN","TENANT")
				.antMatchers("/bug/getBugsOfFirm/**").hasAuthority("FIRM")
				.antMatchers("/bill/makeBill/**").hasAuthority("FIRM")
				.antMatchers("/bug/getBugsOfResponsiblePerson/**").hasAnyAuthority("INSTITUTION","TENANT")
				.antMatchers("/user/getAllFirms").hasAnyAuthority("INSTITUTION","TENANT")
				.antMatchers("/bug/connectBugAndFirm/**").hasAnyAuthority("INSTITUTION","TENANT")
				.antMatchers("/user/getApartmentsOfTenant/**").hasAuthority("TENANT")
				.antMatchers("/bug/getAllBugs/**").hasAuthority("TENANT")
				.antMatchers("/bug/reportBug/**").hasAuthority("TENANT")
				.antMatchers("/bug/deleteBug/**").hasAuthority("TENANT")
				.antMatchers("/bug/addComment/**").hasAnyAuthority("INSTITUTION","TENANT","FIRM")
				.antMatchers("/bug/getBug/**").hasAnyAuthority("INSTITUTION","TENANT","FIRM")
				.antMatchers("/bug/deleteComment/**").hasAnyAuthority("INSTITUTION","TENANT","FIRM")
				.antMatchers("/bill/getBill/**").hasAnyAuthority("TENANT","FIRM")
				.antMatchers("/bill/payBill/**").hasAuthority("TENANT")



				.antMatchers("/parlament/**").permitAll()
				.antMatchers("/session/**").permitAll()
				.antMatchers("/topic/**").permitAll()
				.anyRequest().authenticated();
				 
		
		// Custom JWT based authentication
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
				UsernamePasswordAuthenticationFilter.class);
	}
	

}
