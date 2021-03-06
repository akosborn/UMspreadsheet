package com.umspreadsheet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

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
                        .antMatchers("/", "/h2-console", "/signin/**", "/signup/**", "/resources/**", "/disconnect/facebook",
                                "/shows/**", "/songs/**", "/wormblog/**", "/about/**", "/signup-confirm", "/song/*/*",
                                "/user/*", "/reset-password", "/change-password", "/playlists").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/track-reviews").hasAnyRole("USER_PRIVILEGE", "MOD_PRIVILEGE")
                    .antMatchers(HttpMethod.PUT, "/api/track-reviews/**").hasAnyRole("USER_PRIVILEGE", "MOD_PRIVILEGE")
                    .antMatchers(HttpMethod.GET,"/api/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/webhooks/twitter").permitAll()
                    .antMatchers(HttpMethod.DELETE, "/api/**").hasRole("MOD_PRIVILEGE")
                    .antMatchers(HttpMethod.POST, "/api/**").hasRole("MOD_PRIVILEGE")
                    .antMatchers(HttpMethod.PUT, "/api/**").hasRole("MOD_PRIVILEGE")
                    .antMatchers("/admin", "/admin/edit-show-angular").hasRole("MOD_PRIVILEGE")
                    .antMatchers("/admin/manage-users/**").hasRole("MANAGE_USERS_PRIVILEGE")
                    .antMatchers("/update-password", "/save-password").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                    .antMatchers("/**").authenticated()
                .and()
                    .rememberMe()
                .and()
                    .csrf()
                        .ignoringAntMatchers("/api/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());


                http.sessionManagement()
                        .maximumSessions(2)
                        .maxSessionsPreventsLogin(false)
                        .expiredUrl("/signin")
                        .sessionRegistry(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry()
    {
        return new SessionRegistryImpl();
    }

    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher()
    {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
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
