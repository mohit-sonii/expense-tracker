package springProject.Expense_Tracker.Builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import springProject.Expense_Tracker.Entities.User;

import java.util.UUID;

@Getter
@Setter
public class UserBuilder {
    private String dummyUsername = "";
    private String dummyPassword = "";

    public User build() {
        User user = new User();
        user.setUser_id(UUID.randomUUID());
        user.setUsername(dummyUsername);
        user.setPassword(dummyPassword);
        return user;
    }

}
