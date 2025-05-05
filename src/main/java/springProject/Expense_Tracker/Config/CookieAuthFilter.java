package springProject.Expense_Tracker.Config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springProject.Expense_Tracker.Util.TokenCookie;

import java.io.IOException;
import java.util.Collections;

@Component
public class CookieAuthFilter extends OncePerRequestFilter {

    @Autowired
    private TokenCookie tokenCookie;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getRequestURI().startsWith("/join")){
            filterChain.doFilter(request,response);
            return;
        }
        Cookie[] cookie = request.getCookies();
        boolean foundCookie = false;
        if(cookie!=null){
            for(Cookie item:cookie){
                if("auth_for_exp_track".equals(item.getName())){
                    if(!tokenCookie.isValidCookie(item.getValue())){
                        response.sendError(HttpServletResponse.SC_FORBIDDEN);
                        return;
                    }
                    Claims claims = tokenCookie.getClaims(item.getValue());
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(claims.getSubject(), null, Collections.singleton(new SimpleGrantedAuthority("USER")));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    foundCookie=true;
                    break;
                }
            }
        }
        if(!foundCookie && request.getRequestURI().startsWith("/c")){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request,response);
    }
}
