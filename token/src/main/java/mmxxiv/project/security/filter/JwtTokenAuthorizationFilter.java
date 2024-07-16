package mmxxiv.project.security.filter;

import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mmxxiv.project.core.property.JwtConfiguration;
import mmxxiv.project.security.token.converter.TokenConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static mmxxiv.project.security.util.SecurityContextUtil.setSecurityContext;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {
    protected final JwtConfiguration jwtConfiguration;
    protected final TokenConverter tokenConverter;
    @Override
    @SuppressWarnings("Duplicates")
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(jwtConfiguration.getHeader().getName());
        if (header != null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())){
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();
        setSecurityContext(equalsIgnoreCase("signed", jwtConfiguration.getType()) ? validate(token) : decryptValidating(token));
        chain.doFilter(request, response);
    }

    @SneakyThrows
    private SignedJWT decryptValidating(String encryptedToken){
        String signedToken = tokenConverter.decryptToken(encryptedToken);
        tokenConverter.validateTokenSignature(signedToken);
        return SignedJWT.parse(signedToken);
    }

    @SneakyThrows
    private SignedJWT validate(String signedToken){
        tokenConverter.validateTokenSignature(signedToken);
        return SignedJWT.parse(signedToken);
    }
}
