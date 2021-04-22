import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

public class UserDAOTest {

    private User user;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private userCreation userCreation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        user = new User("firstname", "lastname",
                "username", "password");
    }

    @Test
    public void createValidFlightFromGUI() {
        JButton buttonAddUser = userCreation.getButtonAddUser();

        userCreation.getTxtfirstname().setText("test_first_name");
        userCreation.getTxtlastname().setText("test_last_name");
        userCreation.getTxtusername().setText("username");
        userCreation.getTxtpassword().setText("password");

        Mockito.when(userDAO.insertNewUser(Mockito.any(User.class))).thenReturn(true);
        buttonAddUser.doClick();
        Mockito.verify(userDAO).insertNewUser(Mockito.any(User.class));

    }
}
