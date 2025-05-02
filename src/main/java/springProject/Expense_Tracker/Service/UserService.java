package springProject.Expense_Tracker.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.UserRepo;
import springProject.Expense_Tracker.Util.TokenFormation;
import java.util.*;


@Service
public class UserService  {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenFormation tokenFormation;

    private final PasswordEncoder passwordEncoder;
    public UserService(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }

    public void saveUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
    }

    public boolean validatePassword(String password,String storedPassword){
        return passwordEncoder.matches(password,storedPassword);
    }

    
    public void createCookie(String token, HttpServletResponse response){
        Cookie cookie=new Cookie("authentication",token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 10);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public boolean isValidCookie(String cookie){
        return tokenFormation.validateToken(cookie);
    }

    public Optional<User> findUser(String username){
        try{
            return userRepo.findUserByUsername(username);
        }catch(Exception e){
            return Optional.empty();
        }
        
    }
}
