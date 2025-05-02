package springProject.Expense_Tracker.Config;

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
        boolean found=false;
        if(cookie!=null){
            for(Cookie item:cookie){
                if("auth_for_exp_track".equals(item.getName())){
                    if(!tokenCookie.isValidCookie(item.getValue())){
                        response.sendError(HttpServletResponse.SC_FORBIDDEN);
                        return;
                    }
                    found=true;
                }
            }
        }
        if(found){
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken("user", null, Collections.singleton(new SimpleGrantedAuthority("USER")));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
            return;
        }
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        return;
    }
}
