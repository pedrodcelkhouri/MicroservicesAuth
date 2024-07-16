package mmxxiv.project.security.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mmxxiv.project.core.property.JwtConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.cors.CorsConfiguration;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SecurityTokenConfig {
    protected final JwtConfiguration jwtConfiguration;

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(STATELESS))
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(
                        (req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(jwtConfiguration.getLoginUrl()).permitAll()
                        .requestMatchers("/sbms/v1/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated());
    }
}
