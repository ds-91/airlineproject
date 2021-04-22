import airline.Main;
import javax.swing.JMenuItem;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTests {

    private Main main;

    @BeforeEach
    public void setup() {
        main = new Main();
    }

    @Test
    public void testGetPane() {
        Assertions.assertNotNull(main.getPane());
    }

    @Test
    public void testGetMenuCustomerMenu() {
        Assertions.assertNotNull(main.getMenuCustomerMenu());
    }

    @Test
    public void testGetMenuTicketsMenu() {
        Assertions.assertNotNull(main.getMenuTicketsMenu());
    }

    @Test
    public void testGetMenuFlightMenu() {
        Assertions.assertNotNull(main.getMenuFlightMenu());
    }

    @Test
    public void testGetMenuUserMenu() {
        Assertions.assertNotNull(main.getMenuUserMenu());
    }

    @Test
    public void testGetMenuBar() {
        Assertions.assertNotNull(main._getMenuBar());
    }

    @Test
    public void testGetMenuItemAddCustomer() {
        Assertions.assertNotNull(main.getMenuItemAddCustomer());
    }

    @Test
    public void testGetMenuItemSearchCustomer() {
        Assertions.assertNotNull(main.getMenuItemSearchCustomer());
    }

    @Test
    public void testGetMenuItemBookTicket() {
        Assertions.assertNotNull(main.getMenuItemBookTicket());
    }

    @Test
    public void testGetMenuItemAddFlight() {
        Assertions.assertNotNull(main.getMenuItemAddFlight());
    }

    @Test
    public void testGetMenuItemUserCreation() {
        Assertions.assertNotNull(main.getMenuItemUserCreation());
    }

    @Test
    public void testGetMenuItemTicketReport() {
        Assertions.assertNotNull(main.getMenuItemTicketReport());
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

    @After
    public void teardown() {
        main = null;
    }
}
