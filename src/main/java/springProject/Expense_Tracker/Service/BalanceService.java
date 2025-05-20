package springProject.Expense_Tracker.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.UserRepo;

import java.util.UUID;

@Service
public class BalanceService {
    @Autowired
    private UserRepo userRepo;
    public ResponseEntity<User> findUserById(UUID id){
        try{
            User user = userRepo.findById(id).orElse(null);
            if(user!=null){
                return new ResponseEntity<>(user,HttpStatus.FOUND);
            }else{
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }

        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
