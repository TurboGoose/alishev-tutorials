package ru.turbogoose.jwt.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.turbogoose.jwt.services.PersonDetailsService;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final PersonDetailsService personDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            Optional<String> optionalToken = extractJwtToken(authHeader);
            if (optionalToken.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token");
            } else {
                try {
                    String username = jwtUtil.validateTokenAndRetrieveUsername(optionalToken.get());
                    UserDetails userDetails = personDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                    SecurityContext secContext = SecurityContextHolder.getContext();
                    if (secContext.getAuthentication() == null) {
                        secContext.setAuthentication(authToken);
                    }
                } catch (JWTVerificationException exc) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token");
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private Optional<String> extractJwtToken(String authHeader) {
        Pattern pattern = Pattern.compile("^Bearer (?<token>.+)$");
        Matcher matcher = pattern.matcher(authHeader);
        if (matcher.matches()) {
            return Optional.of(matcher.group("token"));
        }
        return Optional.empty();
    }
}
