import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UserTest {

    @Test
    public void firstNameValid() {
        User user = new User(1, "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getFirstName());
        Assertions.assertNotEquals(null, user.getFirstName());
    }

    @Test
    public void lastNameValid() {
        User user = new User(1, "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getLastName());
        Assertions.assertNotEquals(null, user.getLastName());
    }

    @Test
    public void usernameValid() {
        User user = new User(1, "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getUsername());
        Assertions.assertNotEquals(null, user.getUsername());
    }

    @Test
    public void passwordValid() {
        User user = new User(1, "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getPassword());
        Assertions.assertNotEquals(null, user.getPassword());
    }

    @Test
    public void firstNameValidTwo() {
        User user = new User(1, "firstName", "lastName", "username", "password");

        Assertions.assertEquals("firstName", user.getFirstName());
    }
}
