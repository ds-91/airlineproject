
import airline.User;
import airline.UserDAO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;


public class UserDAOTest {

    private Connection con;


    private UserDAO userDAO = new UserDAO();


    private User user = new User("id", "firstname", "lastname",
            "username", "password");

    public UserDAOTest() {
    }

    //Testing that insert user can be correctly inserted into the database
    @Test
    public void validInsertUser() {
        Assertions.assertTrue(userDAO.insertNewUser(user));
    }


}