package org.zerock.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//아이디, 암호가 입력시 조건에 따라 이동될 html 파일들이 결정된다.
		http.authorizeRequests().antMatchers("/guest/**").permitAll()
			.and()
			.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER")
			.and()
			.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/success").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/accessDenied")
			.and()
			.logout().logoutUrl("/logout").invalidateHttpSession(true);
			
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() { //입력한 암호가 맞는지 검사를 해줍니다.
		
		return new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// TODO Auto-generated method stub
				return rawPassword.equals(encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return rawPassword.toString();
			}
		};
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// 페이지에 입력될 아이디와 암호들
		auth.inMemoryAuthentication()
			.withUser("manager").password("1111").roles("MANAGER")
			.and()
			.withUser("guest").password("2222").roles("BASIC")
			.and()
			.withUser("admin").password("3333").roles("ADMIN");
	}
}
