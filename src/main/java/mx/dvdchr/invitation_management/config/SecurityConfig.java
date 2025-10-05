package mx.dvdchr.invitation_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;
import mx.dvdchr.invitation_management.enums.UserRoles;
import mx.dvdchr.invitation_management.filter.JWTAuthorizationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.disable())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/api/auth/login", "/api/auth/logout", "/api/auth/register", "/swagger-ui/**", "/v3/api-docs/**")
                                .permitAll()
                                .requestMatchers("/api/events/**")
                                .hasAnyRole(UserRoles.ADMIN.name(), UserRoles.ORGANIZER.name())
                                .requestMatchers("/api/seats/**") // * Includes /api/seats/category
                                .hasAnyRole(UserRoles.ADMIN.name(), UserRoles.ORGANIZER.name())
                                .requestMatchers("/api/guests/**")
                                .hasAnyRole(UserRoles.ADMIN.name(), UserRoles.ORGANIZER.name(), UserRoles.GUEST.name())
                                .requestMatchers("/api/invitations/**")
                                .hasAnyRole(UserRoles.ADMIN.name(), UserRoles.ORGANIZER.name(), UserRoles.GUEST.name())
                                .requestMatchers("/api/roles/**")
                                .hasAnyRole(UserRoles.ADMIN.name())
                                .requestMatchers("/api/users/**")
                                .hasAnyRole(UserRoles.ADMIN.name(), UserRoles.ORGANIZER.name(), UserRoles.GUEST.name())
                                .anyRequest()
                                .authenticated())
                .exceptionHandling(
                        exception -> exception.authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("""
                                        {"message": "Access token not provided"}
                                    """);
                        }).accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("""
                                        {"message": "Access Denied"}
                                    """);
                        }))
                .sessionManagement(
                        sessionManagementh -> sessionManagementh
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
