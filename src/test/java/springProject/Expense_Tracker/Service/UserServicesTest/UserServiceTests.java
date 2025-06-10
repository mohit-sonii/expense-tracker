package springProject.Expense_Tracker.Service.UserServicesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import springProject.Expense_Tracker.Builder.UserBuilder;
import springProject.Expense_Tracker.Entities.User;
import springProject.Expense_Tracker.Repository.UserRepo;
import springProject.Expense_Tracker.Service.UserService;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTests {

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @ParameterizedTest
    @ArgumentsSource(UserArguments.class)
    public void testFindByUsername(String username){
       if(username.equals("sunita")){
           UserBuilder dummy = new UserBuilder();
           dummy.setDummyUsername("mohit");
           dummy.setDummyPassword("abc");
           User user = dummy.build();
           when(userRepo.findUserByUsername("sunita")).thenReturn(Optional.ofNullable(user));
       }else{
           when(userRepo.findUserByUsername(username)).thenReturn(null);
       }
        User user = userRepo.findUserByUsername(username) .orElse(null);
        assertNotNull(user);
    }
}
