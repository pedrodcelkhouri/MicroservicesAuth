package mmxxiv.project.sbms.security.config;

import mmxxiv.project.core.property.JwtConfiguration;
import mmxxiv.project.security.config.SecurityTokenConfig;
import mmxxiv.project.security.filter.JwtTokenAuthorizationFilter;
import mmxxiv.project.security.token.converter.TokenConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {
    private final TokenConverter tokenConverter;

    public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration, AuthenticationManager authenticationManager, TokenConverter tokenConverter) {
        super(jwtConfiguration, authenticationManager);
        this.tokenConverter = tokenConverter;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

}
