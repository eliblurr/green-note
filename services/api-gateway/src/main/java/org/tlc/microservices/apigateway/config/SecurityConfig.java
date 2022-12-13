package org.tlc.microservices.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.server.WebFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired private JwtEntryPoint jwtEntryPoint;

    @Bean
    public SecurityWebFilterChain authFilterChain(ServerHttpSecurity serverHttpSecurity){
        return serverHttpSecurity.cors().disable().csrf().disable().authorizeExchange(
                ex -> ex.anyExchange().permitAll()
        ).build();
    }

//    @Bean
//    public SecurityWebFilterChain corsWebFilterChain(ServerHttpSecurity serverHttpSecurity){
//        return serverHttpSecurity.cors().and().build();
//    }

//    @Bean
//    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(Collections.singletonList("http://localhost:8100")); // Provide list of origins if you want multiple origins
//        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
//        config.setAllowCredentials(true);
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }


//    @Bean
//    public SecurityFilterChain corsFilterChain(HttpSecurity http) throws Exception {
//        http.cors();
////                .and().csrf().disable();
//        return http.build();
//    }



//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type"));
//        configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

//    @Bean
//    public SecurityWebFilterChain authFilterChain(ServerHttpSecurity serverHttpSecurity){
//
////        serverHttpSecurity.cors()
//
//        return serverHttpSecurity.csrf().disable().exceptionHandling()
////                .authenticationEntryPoint(jwtEntryPoint)
////                .and().addFilterBefore(jwtTokenFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
//                .and().cors().and()
////                .cors("kkj")
////                .cors(corsFilter()).
//                .authorizeExchange(exchange -> exchange.anyExchange().permitAll()).build();
//    }

//    public JwtFilter jwtTokenFilter() { return new JwtFilter();}

//    @Bean
//    public SecurityFilterChain useJwtFilter(HttpSecurity http) throws Exception {
////        System.out.println("\n\n I AM CALLED \n\n");
//        return http.addFilterBefore(jwtTokenFilter(), BasicAuthenticationFilter.class)
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint).and()
//                .authorizeHttpRequests()
////                .anyRequest()
////                .authorizeRequests().antMatchers("/api/v1/auth/**").permitAll()
//                .anyRequest().authenticated().and().build();
//
//
////        http.addFilterBefore(jwtTokenFilter(), BasicAuthenticationFilter.class);
//
////        return http.build();
////        return http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class).build();
//    }

//    @Bean
//    public CorsConfiguration corsConfiguration(){
//        List<String> gbl = new ArrayList<>();
//        gbl.add("*");
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        config.setAllowedHeaders(gbl);
//        config.setAllowedMethods(gbl);
//        config.setAllowedOrigins(gbl);
//        return config;
//    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:4200");
////        GET, POST, PATCH, PUT, DELETE, OPTIONS, HEAD
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("PATCH");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("HEAD");
//
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }

//    @Bean
//    @Order(2)
//    public SecurityWebFilterChain disableCSRFFilterChain(ServerHttpSecurity serverHttpSecurity){
//        return serverHttpSecurity.csrf().disable().build();
//    }

//    @Bean
//    @Order(3)
//    public SecurityWebFilterChain methodFilterChain(ServerHttpSecurity serverHttpSecurity){
//        return serverHttpSecurity.authorizeExchange(
//                exchange -> exchange.anyExchange().permitAll()
//        ).build();
//    }

//    @Bean
//    @Order(2)
//    public SecurityWebFilterChain methodFilterChain(ServerHttpSecurity serverHttpSecurity){
//        return serverHttpSecurity.authorizeExchange(exchange -> exchange
//                .pathMatchers(HttpMethod.POST,
////                        "/api/users/admins", "/api/users/admins/",
//                        "/api/users/customers", "/api/users/customers/",
//                        "/api/users/authenticate/**", "/api/users/authenticate"
//                ).permitAll()
//                .pathMatchers(HttpMethod.GET,
//                        "/eureka/**",
//                        "/api/users/admins/",
//                        "/api/users/admins"
//                )
//                .permitAll()
//        ).build();
//    }

//    @Bean
//    @Order(4)
//    public SecurityWebFilterChain authFilterChain(ServerHttpSecurity serverHttpSecurity){
//        System.out.println("\n\n I am called \n\n");
//        return serverHttpSecurity.authorizeExchange(exchange -> exchange
//                .anyExchange().authenticated()
//        ).build();
//    }

//    public JwtFilter jwtTokenFilter() { return new JwtFilter();}

//    @Bean
//    @Order(1)
//    public SecurityFilterChain useJwtFilter(HttpSecurity http) throws Exception {
////        http.cors().and().csrf().disable()
////                .exceptionHandling().and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
////                .authorizeRequests().antMatchers("/api/v1/auth/**").permitAll()
////                .anyRequest().authenticated();
//
////        http.authenticationProvider(authenticationProvider());
//
//        http
////                .addFilter(jwtTokenFilter());
//                .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }



}
