import airline.Login;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test cases for the Login window.
 */
public class LoginTests {

  private FrameFixture window;
  private Login frame;

  /**
   * Called once when the tests start.
   */
  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  /**
   * Called before each test. Creates a new Login window and shows it.
   */
  @BeforeEach
  public void setUp() {
    frame = GuiActionRunner.execute(() -> new Login());
    window = new FrameFixture(frame);
    window.show();
  }

  /**
   * Inserts incorrect username and password into the fields and tries to log in.
   */
  @Test
  public void testValidLogin() {
    window.textBox("textUser").setText("asdf");
    window.textBox("textPass").setText("asdf");
    window.button("buttonLogin").click();

    String result = null;
    try {
      result = window.label("loginStatus").text();
    } catch (Exception e) {
    }
    Assertions.assertNull(result);
  }

  /**
   * Tests if the user can log in with an empty username.
   */
  @Test
  public void testInvalidLoginEmptyUsername() {
    window.textBox("textUser").setText("");
    window.textBox("textPass").setText("asdf");
    window.button("buttonLogin").click();

    String result = window.label("loginStatus").text();
    Assertions.assertNotNull(result);
  }

  /**
   * Tests if the user can log in with an empty password.
   */
  @Test
  public void testInvalidLoginEmptyPassword() {
    window.textBox("textUser").setText("asdf");
    window.textBox("textPass").setText("");
    window.button("buttonLogin").click();

    String result = window.label("loginStatus").text();
    Assertions.assertNotNull(result);
  }

  /**
   * Tests if the user can edit the username field.
   */
  @Test
  public void canEnterUsername() {
    window.textBox("textUser").setText("asdf");
    Assertions.assertEquals("asdf", window.textBox("textUser").text());
  }

  /**
   * Tests if the user can edit the password field.
   */
  @Test
  public void canEnterPassword() {
    window.textBox("textPass").setText("asdf");
    Assertions.assertEquals("asdf", window.textBox("textPass").text());
  }

  /**
   * Called after each test. Cleans up the window to free memory and allow
   * another to be created for the next test.
   */
  @AfterEach
  public void teardown() {
    window.cleanUp();
  }
}
