import domain.User;
import repository.UserRepository;
import service.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();
        User user = new User("ub","rabie",21);
        //userService.addUser(user);
        //userService.updateUser("ub");
        //userService.deleteUser("bbb");

    }
}