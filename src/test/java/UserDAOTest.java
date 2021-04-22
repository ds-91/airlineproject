import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserDAOTest {

    private Connection con;


    private UserDAO userDAO = new UserDAO();


    private User user = new User("firstname", "lastname",
                                       "username", "password");


    @Test
    public void validInsertUser() {
        Assertions.assertTrue(userDAO.insertNewUser(user));
    }

    @Test
    public void validIdIncrementTest() {
        int current = userDAO.nextIdInDatabase();
        userDAO.insertNewUser(user);
        Assertions.assertEquals(current + 1, userDAO.nextIdInDatabase());

    }

    @AfterEach
    public void teardown() throws SQLException {
        PreparedStatement ps = this.con.prepareStatement("DELETE FROM user " +
                "where username = ?");
        ps.setString(1, "username");
        ps.executeUpdate();

        this.con.close();

    }
}
