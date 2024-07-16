package mmxxiv.project.auth.security.config;

import mmxxiv.project.auth.security.filter.JwtUsernameAndPasswordAuthenticationFilter;
import mmxxiv.project.core.property.JwtConfiguration;
import mmxxiv.project.security.config.SecurityTokenConfig;
import mmxxiv.project.security.token.creator.TokenCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {
    private final UserDetailsService userDetailsService;
    private final TokenCreator tokenCreator;

    public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration,
                                     AuthenticationManager authenticationManager,
                                     UserDetailsService userDetailsService,
                                     TokenCreator tokenCreator) {
        super(jwtConfiguration, authenticationManager);
        this.userDetailsService = userDetailsService;
        this.tokenCreator = tokenCreator;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager, jwtConfiguration, tokenCreator));
        super.configure(http);
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
