import airline.Main;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserCreationTests {
  private FrameFixture window;

  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  //Setting up the userCreation GUI tests by opening the window and clicking on the appropriate location
  @BeforeEach
  public void setUp() {
    Main frame = GuiActionRunner.execute(() -> new Main());
    window = new FrameFixture(frame);
    window.show();

    window.menuItem("userMenu").click();
    window.menuItem("userCreationMenuItem").click();
  }

  //Testing the success of adding test fields in the userCreation field and clicking the buttonAddUser
  @Test
  public void testFirstName() {
    window.textBox("textUserName").setText("TEST_USER_NAME");
    window.button("buttonAdd").click();

    String result = window.label("statustext").text();
    Assertions.assertEquals("pass", result);
  }

  @AfterEach
  public void tearDown() {
    window.cleanUp();
  }
}
