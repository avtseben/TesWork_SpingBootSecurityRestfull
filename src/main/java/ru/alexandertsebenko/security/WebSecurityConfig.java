package ru.alexandertsebenko.security;

import ru.alexandertsebenko.datamodel.Account;
import ru.alexandertsebenko.db.AccountRepository;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.beans.factory.annotation.Autowired;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountRepository accountRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	/*Не получилось авторизовыать пользователей по роли при получении данных их ДБ
	 * и для группы ADMIN и USER одинаково отвечает Access Denied
	 * но аутентификация происходит, если вводит не правильный парол отвечает Bad Credentials
	 * и работает GET метод, попадающий под категорию permitAll()
	auth.userDetailsService(new UserDetailsService() {

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if(account != null) {

		    User user = new User(account.getUsername(), account.getPassword(), true, true, true, true,
			    AuthorityUtils.createAuthorityList(account.getRole()));

		System.out.println(user);
		return user;
		} else {
		    System.out.println("no such user");
		    throw new UsernameNotFoundException("could not find the user '"
				+ username + "'");
		}
	    }

	});
	*/

	//TODO переделать на userDetailsService
	auth
	    .inMemoryAuthentication()
		.withUser("user")
		    .password("userpass")
		    .roles("USER")
		    .and()
		.withUser("admin")
		    .password("adminpass")
		    .roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http.httpBasic().and().authorizeRequests()
	    //GET операции в path satmodems доступны всем
	    .antMatchers(HttpMethod.GET,"/satmodems").permitAll() 
	    .antMatchers(HttpMethod.POST,"/satmodems").hasRole("ADMIN") 
	    .antMatchers(HttpMethod.PUT,"/satmodems").hasRole("ADMIN")
	    .antMatchers(HttpMethod.DELETE,"/satmodems").hasRole("ADMIN").and()
	    .csrf().disable();
    }
}
