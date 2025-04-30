package springProject.Expense_Tracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.UserRepo;

import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    private UserRepo userRepo;

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
        return userRepo.findUserByUsername(username);
    }
}
