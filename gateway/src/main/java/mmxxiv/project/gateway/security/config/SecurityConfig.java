package mmxxiv.project.gateway.security.config;

import mmxxiv.project.core.property.JwtConfiguration;
import mmxxiv.project.gateway.security.filter.GatewayJwtTokenAuthorizationFilter;
import mmxxiv.project.security.config.SecurityTokenConfig;
import mmxxiv.project.security.token.converter.TokenConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends SecurityTokenConfig {
    private final TokenConverter tokenConverter;

    public SecurityConfig(JwtConfiguration jwtConfiguration, AuthenticationManager authenticationManager, TokenConverter tokenConverter) {
        super(jwtConfiguration, authenticationManager);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new GatewayJwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }
}
