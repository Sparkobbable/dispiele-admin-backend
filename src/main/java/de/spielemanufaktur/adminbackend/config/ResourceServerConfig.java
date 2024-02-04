package de.spielemanufaktur.adminbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/**")
                .authorizeHttpRequests()
                .requestMatchers("/oauthproxy/**").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
