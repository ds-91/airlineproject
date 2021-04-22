
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserDAOTest {

    private Connection con;


    private UserDAO userDAO = new UserDAO();


    private User user = new User("id", "firstname", "lastname",
            "username", "password");

    public UserDAOTest() {
    }


    @Test
    public void validInsertUser() {
        Assertions.assertTrue(userDAO.insertNewUser(user));
    }


}