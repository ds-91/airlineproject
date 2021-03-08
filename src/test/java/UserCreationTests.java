import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserCreationTests {
  private FrameFixture window;
  private Connection con;
  private PreparedStatement pst;

  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @BeforeEach
  public void setUp() {
    Main frame = GuiActionRunner.execute(() -> new Main());
    window = new FrameFixture(frame);
    window.show();

  }

  @Test
  public void test() throws InterruptedException {
    window.menuItem("userMenu").click();
    window.menuItem("userCreationMenuItem").click();

    window.textBox("textFirstName").setText("TEST_FIRST_NAME");
    window.textBox("textLastName").setText("TEST_LAST_NAME");
    window.textBox("textUserName").setText("TEST_USER_NAME");
    window.textBox("textPassword").setText("TEST_PASSWORD");

    window.button("buttonAdd").click();

    ResultSet result = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost/airline","root","password");
      pst = con.prepareStatement("SELECT username FROM user WHERE username=?");
      pst.setString(1, "TEST_USER_NAME");
      result = pst.executeQuery();
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(addflight.class.getName()).log(Level.SEVERE, null, ex);
    }
    assert(result != null);
  }
}
