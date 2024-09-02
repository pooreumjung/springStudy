package hello.hello_spring.config;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse,@NotNull FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Optional<Object> userIdOptional = Optional.ofNullable(session.getAttribute("userId"));
        if (userIdOptional.isEmpty()) {
            SecurityContextHolder.getContext().setAuthentication(null);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userIdOptional.get().toString(), "", List.of());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
