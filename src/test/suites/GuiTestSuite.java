import javax.swing.JMenuItem;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GuiTestSuite {

    private FrameFixture window;
    private Login frame;
    private Main main;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        frame = GuiActionRunner.execute(() -> new Login());
        main = new Main();
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

    @Test
    public void clickAddCustomerMenuItem() {
        JMenuItem item = main.getMenuItemAddCustomer();
        item.doClick();

        Assertions.assertTrue(main.menuItemAddCustomerActionPerformed());
    }

    @Test
    public void clickSearchCustomerMenuItem() {
        JMenuItem item = main.getMenuItemSearchCustomer();
        item.doClick();

        Assertions.assertTrue(main.menuItemSearchCustomerActionPerformed());
    }

    @Test
    public void clickAddFlightMenuItem() {
        JMenuItem item = main.getMenuItemAddFlight();
        item.doClick();

        Assertions.assertTrue(main.menuItemAddFlightActionPerformed());
    }

    @Test
    public void clickBookTicketMenuItem() {
        JMenuItem item = main.getMenuItemBookTicket();
        item.doClick();

        Assertions.assertTrue(main.menuItemBookTicketActionPerformed());
    }

    @Test
    public void clickTicketReportMenuItem() {
        JMenuItem item = main.getMenuItemTicketReport();
        item.doClick();

        Assertions.assertTrue(main.menuItemTicketReportActionPerformed());
    }

    @Test
    public void clickUserCreationMenuItem() {
        JMenuItem item = main.getMenuItemUserCreation();
        item.doClick();

        Assertions.assertTrue(main.menuItemUserCreationActionPerformed());
    }

    @AfterEach
    public void teardown() {
        window.cleanUp();
    }
}
