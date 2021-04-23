import airline.Customer;
import airline.CustomerDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Date;

/**
 * Mock a customer data access object
 */
public class MockCustomerDAOTests {

    @Mock
    private CustomerDAO mockDAO;

    @InjectMocks

    private airline.addCustomer addCustomer;

    private Customer customer;
    private Robot robot;

    @BeforeEach
    public void setup() throws AWTException {
        MockitoAnnotations.initMocks(this);
        byte [] userimage = new byte[]{};
        String customerID = "CS003";
        customer = new Customer(customerID, "first_name",
                "last_name", "gender", "address",
                "dob", 1234567, userimage, "nic", "passport");
        robot = new Robot();
    }

    /**
     * Test a successful database call for adding a new customer
     */
    @Test
    public void createValidCustomerFromGUI() {
        JButton buttonAddCustomer = addCustomer.getButtonAddCustomer();

        addCustomer.getTxtCustFirstname().setText("TEST_CUSTOMER_FIRSTNAME");
        addCustomer.getTxtCustLastname().setText("TEST_CUSTOMER_LASTNAME");
        addCustomer.getTxtdob().setDate(new Date(1997, 3, 30));
        addCustomer.getTxtCustAddress().setText("42 Wallaby Way, Sydney");
        addCustomer.getTxtCustContact().setText("1234567");
        addCustomer.getTxtCustNic().setText("999999");
        addCustomer.getTxtCustPassport().setText("8008135");


        Mockito.when(mockDAO.createCustomer(Mockito.any(Customer.class))).thenReturn(true);
        buttonAddCustomer.doClick();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Mockito.verify(mockDAO).createCustomer(Mockito.any(Customer.class));
    }

    @AfterEach
    public void teardown() {
        mockDAO = null;
        addCustomer = null;
        customer = null;
    }
}