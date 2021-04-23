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

    //Testing insertion of user into the database
    @Test
    public void validUserCreation() {
        boolean success = this.userDAO.insertNewUser(testUser);

        Assertions.assertTrue(success);
    }

    //Testing failed branch of inserting a new user into the database of null User
    @Test
    public void invalidNullUserCreation() {
        boolean success = this.userDAO.insertNewUser(null);

        Assertions.assertFalse(success);
    }

    //Testing failed branch of inserting a new user into the database with empty Strings in member variable
    @Test
    public void invalidEmptyFieldUserCreation() {
        testUser.setFirstName("");

        Assertions.assertFalse(this.userDAO.insertNewUser(testUser));
    }

    //Testing failed branch of inserting a new user into the database with null String in member variable
    @Test
    public void invalidNullFieldUserCreation() {
        testUser.setUsername(null);

        Assertions.assertFalse(this.userDAO.insertNewUser(testUser));
    }

    //Testing valid first name of type User
    @Test
    public void firstNameValid() {
        User user = new User( "id", "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getFirstName());
        Assertions.assertNotEquals(null, user.getFirstName());
    }

    //Testing valid last name of type User
    @Test
    public void lastNameValid() {
        User user = new User( "id", "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getLastName());
        Assertions.assertNotEquals(null, user.getLastName());
    }

    //Testing valid username of type User
    @Test
    public void usernameValid() {
        User user = new User( "id", "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getUsername());
        Assertions.assertNotEquals(null, user.getUsername());
    }

    //Testing valid password of type User
    @Test
    public void passwordValid() {
        User user = new User( "id","firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getPassword());
        Assertions.assertNotEquals(null, user.getPassword());
    }

    //Testing setter methods of User object
    @Test
    public void validSetFlightInformation() {
        testUser.setUsername("username");
        testUser.setLastName("lastname");
        testUser.setPassword("password");
        testUser.setFirstName("firstname");

        Assertions.assertEquals("username", testUser.getUsername());
        Assertions.assertEquals("lastname", testUser.getLastName());
        Assertions.assertEquals("password", testUser.getPassword());
        Assertions.assertEquals("firstname", testUser.getFirstName());
    }

    //Removes test users added to the database
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