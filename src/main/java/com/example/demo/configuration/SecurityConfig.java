package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 http.authorizeHttpRequests(
 auth-> auth.requestMatchers("/signin", "/signup", "/graphql", "/graphiql").permitAll()
 //.requestMatchers("/api/legends/**").hasRole("ADMIN")
 .requestMatchers("/api/users" ).hasRole("USER")
 .anyRequest().authenticated())
 .httpBasic(withDefaults()).csrf(csrf-> csrf.disable())
 .formLogin(withDefaults())
 .rememberMe(withDefaults())
 .logout(logout-> logout.logoutUrl("/signout").permitAll());
 return http.build();
 }


     @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}