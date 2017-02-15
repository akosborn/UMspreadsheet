package com.umspreadsheet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter
{
    private DataSource dataSource;

    @Autowired
    public SocialConfig(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    /**
     *@link http://stackoverflow.com/questions/32353349/spring-boot-with-spring-social-google-provider
     *@link https://developers.google.com/identity/protocols/googlescopes
     *@link http://nixmash.com/java/adding-google-sign-in-to-spring-social/
     */
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment)
    {
        GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory(
                environment.getProperty("spring.social.google.app-id"),
                environment.getProperty("spring.social.google.app-secret"));
        googleConnectionFactory.setScope("https://www.googleapis.com/auth/userinfo.email");
        connectionFactoryConfigurer.addConnectionFactory(googleConnectionFactory);
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Google google(ConnectionRepository repository)
    {
        Connection<Google> connection = repository.findPrimaryConnection(Google.class);
        return connection != null ? connection.getApi() : null;
    }

    @Override
    public UserIdSource getUserIdSource()
    {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator)
    {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator,
                                                   UsersConnectionRepository usersConnectionRepository)
    {
        return new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
    }

    @Bean
    public SignInAdapter signInAdapter()
    {
        return new SimpleSignInAdapter(new HttpSessionRequestCache());
    }
}
