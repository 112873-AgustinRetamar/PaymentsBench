package com.bench.zuul.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/api/security/oauth/**", "/api/clients/findByEmail/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/clients/**", "/api/accounts/**", "/api/payments/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/clients/{id}", "/api/accounts/{id}", "/api/payments/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/clients/**", "/api/payments/**", "/api/accounts/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated();
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    // Se genera con el componente Spring
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        // Asigna la llave de la firma
        tokenConverter.setSigningKey("super_secreto");
        return tokenConverter;
    }

}
