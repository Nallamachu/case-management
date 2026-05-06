package com.softifyo.mgmt.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

import static com.softifyo.mgmt.constants.Permission.*;
import static com.softifyo.mgmt.constants.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "/api/v1/user/**",
            "/api/v1/file/**",
            "/api/v1/dashboard/**",
            "/api/v1/asset/**",
            "/api/v1/service/**",
            "/api/v1/rental/**",
            "/api/v1/client/**",
            "/api/v1/expense/**",
            "/api/v1/payment/**",
            "/api/v1/report/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};

    @Autowired
    private final JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    private final AuthenticationProvider authenticationProvider;
    @Autowired
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers("/api/v1/auth/**").hasAnyRole(ADMIN.name(), MANAGER.name(),USER.name())
                                .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                                .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                                .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                                .requestMatchers("/api/v1/user/**").hasAnyRole(ADMIN.name(), MANAGER.name(),USER.name())
                                .requestMatchers(GET, "/api/v1/user/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/api/v1/user/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/user/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/user/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())
                                .requestMatchers("/api/v1/file/**").hasAnyRole(ADMIN.name(), MANAGER.name(),USER.name())
                                .requestMatchers(GET, "/api/v1/file/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/api/v1/file/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/file/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/file/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())
                                .requestMatchers("/api/v1/asset/**").hasAnyRole(ADMIN.name(), MANAGER.name(), USER.name())
                                .requestMatchers(GET, "/api/v1/asset/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/api/v1/asset/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/asset/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/asset/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())
                                .requestMatchers("/api/v1/dashboard/**").hasAnyRole(ADMIN.name(), MANAGER.name(), USER.name())
                                .requestMatchers(GET, "/api/v1/dashboard/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/api/v1/dashboard/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/dashboard/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/dashboard/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())
                                .requestMatchers("/api/v1/service/**").hasAnyRole(ADMIN.name(), MANAGER.name(), USER.name())
                                .requestMatchers(GET, "/api/v1/service/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/api/v1/service/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/service/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/service/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())
                                .requestMatchers("/api/v1/rental/**").hasAnyRole(ADMIN.name(), MANAGER.name(), USER.name())
                                .requestMatchers(GET, "/api/v1/rental/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/api/v1/rental/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/rental/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/rental/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())
                                .requestMatchers("/api/v1/client/**").hasAnyRole(ADMIN.name(), MANAGER.name(), USER.name())
                                .requestMatchers(GET, "/api/v1/client/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/api/v1/client/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/client/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/client/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())
                                .requestMatchers("/api/v1/expense/**").hasAnyRole(ADMIN.name(), MANAGER.name(), USER.name())
                                .requestMatchers(GET, "/api/v1/expense/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/api/v1/expense/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/expense/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/expense/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())
                                .requestMatchers("/api/v1/payment/**").hasAnyRole(ADMIN.name(), MANAGER.name(), USER.name())
                                .requestMatchers(GET, "/api/v1/payment/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/api/v1/payment/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/payment/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/payment/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())
                                .requestMatchers("/api/v1/report/**").hasAnyRole(ADMIN.name(), MANAGER.name(), USER.name())
                                .requestMatchers(GET, "/api/v1/report/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/api/v1/report/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/report/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/report/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Collections.singletonList("*"));  // mirrors the origin
        config.setAllowCredentials(true);
        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        config.setAllowedHeaders(List.of(
                "Authorization",
                "Content-Type",
                "X-Requested-With",
                "ngrok-skip-browser-warning"
        ));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
