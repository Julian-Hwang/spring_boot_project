package org.zerock.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new PasswordEncoder() {
			// 로그인시 아이디와 비밀번호가 맞는지를 검사해준다.
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
		// 아이디와 비밀번호 만들기
		auth.inMemoryAuthentication().withUser("manager").password("1111").roles("MANAGER")
		.and()
		.withUser("guest").password("2222").roles("BASIC")
		.and()
		.withUser("admin").password("3333").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// hasAnyRole을 이용해서 특정 사이트에 들어갈 수 있는 유저들을 설정한다.
		http.authorizeRequests().antMatchers("/boards/list").hasAnyRole("MANAGER", "ADMIN", "BASIC")
			.and()
			.authorizeRequests().antMatchers("/boards/modify").hasAnyRole("MANAGER", "ADMIN")
			.and()
			.authorizeRequests().antMatchers("/boards/delete").hasAnyRole("MANAGER","ADMIN")
			.and()
			.csrf()
			.ignoringAntMatchers("/boards/delete")
			.ignoringAntMatchers("/boards/modify")
            //CSRF 방지 처리를 적용하는 경우와
            //안 하는 경우를 request url 차원에서 나눠서 처리할 수 있게 된다.
			.and()
			.formLogin().loginPage("/boards/login").defaultSuccessUrl("/boards/list").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/boards/accessDenied")
			.and()
			.logout().logoutUrl("/boards/logout").invalidateHttpSession(true);
	}

}
