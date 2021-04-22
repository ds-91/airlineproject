import airline.DatabaseManager;
import airline.User;
import airline.UserDAO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserTest {

    private DatabaseManager databaseManager = new DatabaseManager();
    private Connection con = databaseManager.getDatabaseConnection();
    private UserDAO userDAO = new UserDAO();
    private User testUser = new User( "id","firstname", "lastname", "username",
            "password");

    public UserTest() {
    }

    @Test
    public void validUserCreation() {
        boolean success = this.userDAO.insertNewUser(testUser);

        Assertions.assertTrue(success);
    }

    @Test
    public void invalidNullUserCreation() {
        boolean success = this.userDAO.insertNewUser(null);

        Assertions.assertFalse(success);
    }

    @Test
    public void invalidEmptyFieldUserCreation() {
        testUser.setFirstName("");

        Assertions.assertFalse(this.userDAO.insertNewUser(testUser));
    }

    @Test
    public void invalidNullFieldFlightCreation() {
        testUser.setUsername(null);

        Assertions.assertFalse(this.userDAO.insertNewUser(testUser));
    }



    @Test
    public void firstNameValid() {
        User user = new User( "id", "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getFirstName());
        Assertions.assertNotEquals(null, user.getFirstName());
    }

    @Test
    public void lastNameValid() {
        User user = new User( "id", "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getLastName());
        Assertions.assertNotEquals(null, user.getLastName());
    }

    @Test
    public void usernameValid() {
        User user = new User( "id", "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getUsername());
        Assertions.assertNotEquals(null, user.getUsername());
    }

    @Test
    public void passwordValid() {
        User user = new User( "id","firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getPassword());
        Assertions.assertNotEquals(null, user.getPassword());
    }

    @Test
    public void firstNameValidTwo() {
        User user = new User( "id", "firstName", "lastName", "username", "password");

        Assertions.assertEquals("firstName", user.getFirstName());
    }

    @Test
    public void validSetFlightInformation() {
        testUser.setUsername("username");
        testUser.setLastName("lastname");
        testUser.setPassword("password");
        testUser.setFirstName("firstname");
    }

    @Test
    public void teardown() throws SQLException {
        PreparedStatement ps = con.prepareStatement("delete from airline.user where username = \"username\";");
        ps.executeUpdate();
        this.con.close();
        this.con = null;
        this.userDAO = null;
        this.testUser = null;
    }
}