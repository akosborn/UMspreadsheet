package com.umspreadsheet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private UserDetailsService userDetailsService;
    private AuthenticationFailureHandler authFailureHandler;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, AuthenticationFailureHandler authFailureHandler)
    {
        this.userDetailsService = userDetailsService;
        this.authFailureHandler = authFailureHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    // Allow access to all front-end resources
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web
                .ignoring()
                    .antMatchers("/**/*.css", "/**/*.png", "/**/*.gif", "/**/*.jpg", "/webjars/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .formLogin()
                    .loginPage("/signin")
                    .loginProcessingUrl("/signin/authenticate")
                    .defaultSuccessUrl("/", false)
                    .failureHandler(authFailureHandler)
                .and()
                    .logout()
                        .logoutUrl("/signout")
                        .deleteCookies("JSESSIONID")
                .and()
                    .authorizeRequests()
                        .antMatchers("/", "/signin/**", "/signup/**", "/resources/**", "/disconnect/facebook",
                                "/shows/**", "/songs/**", "/wormblog/**", "/about/**", "/signup-confirm", "/song/*/*", "/user/*").permitAll()
                    .antMatchers("/admin").hasRole("MOD_PRIVILEGE")
                    .antMatchers("/admin/manage-users/**").hasRole("MANAGE_USERS_PRIVILEGE")
                    .antMatchers("/**").authenticated()
                .and()
                    .rememberMe();
    }

    /**
    private UserDetailsService userDetailsService;
    private DataSource dataSource;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, DataSource dataSource)
    {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Defines custom authentication as UserDetailsService
     * Defines password encoding type as BCryptPasswordEncoder
     */
    /**
    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                    .antMatchers("/", "/register", "/reviews", "/reviews/*", "/user/review/**")
                .permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")    // directs Spring to login page
                    .permitAll()    // grants access for all to login page
                    .and()
                .logout()
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    **/
}
