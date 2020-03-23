package com.trackodds.trackodds.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

//    @Autowired
//    DataSource dataSource;
    
    @Autowired
    CustomLoginSuccessHandler sucessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
		//auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		
		  http.authorizeRequests() 
		 .antMatchers("/admin").hasRole("ADMIN")
		 .antMatchers("/user").hasAnyRole("ADMIN", "USER")
		 //.antMatchers("/profile").hasAnyRole("ADMIN", "USER")
		 .antMatchers("/").permitAll()
		 .antMatchers("/login").permitAll()
		 .and().csrf().disable()
		 .formLogin().loginPage("/login")
		 .failureUrl("/login?error=true").successHandler(sucessHandler)
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
			// logout
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").and()
			.exceptionHandling()
			.accessDeniedPage("/access-denied");
		
        		/*.antMatchers("/resources/**").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
                .antMatchers("/home*").hasRole("USER").and()
                .formLogin()
                	.loginPage("/login")
                	.loginProcessingUrl("/authentication")
                	.permitAll()
        		.and()
        		.logout()
        		.permitAll();*/
    }
    
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
  
    

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
