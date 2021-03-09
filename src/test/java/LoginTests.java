import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests {
  private FrameFixture window;
  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @BeforeEach
  public void setUp() {
    Login frame = GuiActionRunner.execute(() -> new Login());
    window = new FrameFixture(frame);
    window.show();
  }

  @Test
  public void testLogin() {
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
}
