package springProject.Expense_Tracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.UserRepo;
import springProject.Expense_Tracker.Util.TokenCookie;
import java.util.*;


@Service
public class UserService  {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenCookie tokenCookie;

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

    public Optional<User> findUser(String username){
        try{
            return userRepo.findUserByUsername(username);
        }catch(Exception e){
            return Optional.empty();
        }
    }

    public User findUserById(UUID id){
        try{
            return userRepo.findById(id).orElse(null);
        }catch(Exception e){
            return null;
        }
    }


}
