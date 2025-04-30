package springProject.Expense_Tracker.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.POJO.SignUpLoginPOJO;
import springProject.Expense_Tracker.Service.UserService;

import java.util.Optional;

@RequestMapping("/join")
@RestController
public class AuthControllers {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpLoginPOJO data){
        try{
            Optional<User> result = userService.findUser(data.getUsername());
            if(result.isPresent()){
                return new ResponseEntity<>("User Already Exists !!", HttpStatus.FORBIDDEN);
            }
            User user = new User();
            if(data.getPassword()==null){
                return new ResponseEntity<>("Please Provide Password !!", HttpStatus.FORBIDDEN);
            }
            user.setUsername(data.getUsername());
            user.setPassword(data.getPassword());
            try{
                userService.saveUser(user);
                return new ResponseEntity<>("User Created Successfully !!",HttpStatus.CREATED);
            }catch(Exception e){
                return new ResponseEntity<>("Error while saving the user, Please try again !!",HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Unknown Server Error Occurred !! "+ e,HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SignUpLoginPOJO data){
        try{
            if(data.getUsername().isEmpty()){
                return new ResponseEntity<>("Username cannot be empty",HttpStatus.BAD_REQUEST);
            }
            if(data.getPassword().isEmpty()){
                return new ResponseEntity<>("Password Cannot be empty",HttpStatus.BAD_REQUEST);
            }
            Optional<User> user = userService.findUser(data.getUsername());
            if(user.isEmpty()){
                return new ResponseEntity<>("User does not exists !!",HttpStatus.NO_CONTENT);
            }

            if(userService.validatePassword(data.getPassword(),user.get().getPassword())){
                return new ResponseEntity<>("User logged in",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Either username or password is incorrect",HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Unknown Server Error Occurred !! "+ e,HttpStatus.BAD_GATEWAY);
        }
    }
}
