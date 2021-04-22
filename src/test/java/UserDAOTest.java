
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

    public UserDAOTest() throws SQLException {
    }


    @Test
    public void validInsertUser() {
        Assertions.assertTrue(userDAO.insertNewUser(user));
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