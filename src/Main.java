import repository.UserRepository;
import service.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();
        //userService.addUser();
        userService.updateUser("aa");
    }
}