import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests {

  private FrameFixture window;
  private Login frame;

  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @BeforeEach
  public void setUp() {
    frame = GuiActionRunner.execute(() -> new Login());
    window = new FrameFixture(frame);
    window.show();
  }

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

  @Test
  public void testInvalidLoginEmptyUsername() {
    window.textBox("textUser").setText("");
    window.textBox("textPass").setText("asdf");
    window.button("buttonLogin").click();

    String result = window.label("loginStatus").text();
    Assertions.assertNotNull(result);
  }

  @Test
  public void testInvalidLoginEmptyPassword() {
    window.textBox("textUser").setText("asdf");
    window.textBox("textPass").setText("");
    window.button("buttonLogin").click();

    String result = window.label("loginStatus").text();
    Assertions.assertNotNull(result);
  }

  @Test
  public void canEnterUsername() {
    window.textBox("textUser").setText("asdf");
    Assertions.assertEquals("asdf", window.textBox("textUser").text());
  }

  @Test
  public void canEnterPassword() {
    window.textBox("textPass").setText("asdf");
    Assertions.assertEquals("asdf", window.textBox("textPass").text());
  }

  @AfterEach
  public void teardown() {
    window.cleanUp();
  }
}
