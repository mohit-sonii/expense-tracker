package springProject.Expense_Tracker.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.POJO.SignUpLoginPOJO;
import springProject.Expense_Tracker.Service.UserService;
import springProject.Expense_Tracker.Util.TokenCookie;

import java.util.Optional;

@RequestMapping("/join")
@RestController
public class AuthControllers {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenCookie tokenCookie;


    //Navigate User to main page after login and signup

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody SignUpLoginPOJO data,@CookieValue(value = "auth_for_exp_track",defaultValue = "") String cookie_value,HttpServletResponse response){
        try{
            Optional<User> result = userService.findUser(data.getUsername());
            if(!cookie_value.isEmpty() && tokenCookie.isValidCookie(cookie_value) && (!result.isEmpty())){

                return new ResponseEntity<>("User Logged In With Cookie",HttpStatus.OK);
            }
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
                String token  = tokenCookie.generateToken(user.getUser_id().toString(),user.getUsername());
                tokenCookie.createCookie(token,response);
                return new ResponseEntity<>("User Created Successfully !!",HttpStatus.CREATED);
            }catch(Exception e){
                return new ResponseEntity<>("Error while saving the user, Please try again !!",HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Unknown Server Error Occurred !! "+ e,HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SignUpLoginPOJO data, HttpServletResponse response, @CookieValue(value = "auth_for_exp_track", defaultValue = "") String cookie_value){
        try{
            Optional<User> user = userService.findUser(data.getUsername());
            if(user.isEmpty()){
                return new ResponseEntity<>("User not found !!",HttpStatus.NOT_FOUND);
            }
            if(!cookie_value.isEmpty()){
                if(tokenCookie.isValidCookie(cookie_value)){
                    return  new ResponseEntity<>("User is Authenticated",HttpStatus.OK);
                }
            }
            if(data.getUsername().isEmpty()){
                return new ResponseEntity<>("Username cannot be empty",HttpStatus.BAD_REQUEST);
            }
            if(data.getPassword().isEmpty()){
                return new ResponseEntity<>("Password cannot be empty",HttpStatus.BAD_REQUEST);
            }

            if(userService.validatePassword(data.getPassword(),user.get().getPassword())){
                String token = tokenCookie.generateToken(user.get().getUser_id().toString(),user.get().getUsername());
                tokenCookie.createCookie(token,response);
                return new ResponseEntity<>("User logged in "+ token,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Either username or password is incorrect",HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Unknown Server Error Occurred !! "+ e,HttpStatus.BAD_GATEWAY);
        }
    }
}
