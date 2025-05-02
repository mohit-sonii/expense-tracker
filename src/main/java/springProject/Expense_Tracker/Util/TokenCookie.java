package springProject.Expense_Tracker.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenCookie {
    @Value("${jwt.secret}")
    private String secret_key;

    private SecretKey key;
    @PostConstruct
    public void initKey(){
        key= Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(JwtException | IllegalArgumentException e){
            return false;
        }
    }
    public Claims getClaims(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
    public void createCookie(String token, HttpServletResponse response){
        Cookie cookie=new Cookie("auth_for_exp_track",token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 10);
        response.addCookie(cookie);
    }

    public boolean isValidCookie(String cookie){
        return validateToken(cookie);
    }

    public String generateToken(String id){
        return  Jwts.builder().setSubject(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10 hours
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

}
