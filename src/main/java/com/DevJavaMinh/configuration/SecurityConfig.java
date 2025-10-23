package com.DevJavaMinh.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .cors(Customizer.withDefaults())
      .csrf(csrf -> csrf.ignoringRequestMatchers(
          new AntPathRequestMatcher("/order/**"),
          new AntPathRequestMatcher("/order-controller/**"),
          new AntPathRequestMatcher("/payment/**"),
          new AntPathRequestMatcher("/create-payment-link"),
          new AntPathRequestMatcher("/api/**")
      ))
      .authorizeHttpRequests(auth -> auth
          .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()   // preflight
          .requestMatchers(
              "/swagger-ui/**","/v3/api-docs/**","/swagger-resources/**","/webjars/**",
              "/","/success","/cancel",
              "/order/**",
              "/order-controller/**",
              "/payment/**",
              "/create-payment-link",
              "/api/**"
          ).permitAll()
          .anyRequest().permitAll() // tạm mở hết để test; khi OK thì siết lại
      );
    return http.build();
  }
}
