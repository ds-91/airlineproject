import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTests {

    private Connection con;
    private CustomerDAO customerDAO;
    private Customer testCustomer;
    private String customerID;

    @BeforeEach
    public void setup() {
        DatabaseManager databaseManager = new DatabaseManager();
        byte [] userimage = new byte[]{};
        this.con = databaseManager.getDatabaseConnection();
        this.customerDAO = new CustomerDAO();
        this.customerID = customerDAO.autoID();
        this.testCustomer = new Customer(customerID, "first_name",
                "last_name", "gender", "address",
                "dob", "contact", userimage, "nic", "passport");
    }

    @Test
    public void validCustomerCreation() {
        boolean success = this.customerDAO.createCustomer(testCustomer);

        Assertions.assertTrue(success);
    }

    @Test
    public void invalidNullCustomerCreation() {
        boolean success = this.customerDAO.createCustomer(null);

        Assertions.assertFalse(success);
    }

    @Test
    public void invalidEmptyFieldCustomerCreation() {
        testCustomer.setFirstname("");

        Assertions.assertFalse(this.customerDAO.createCustomer(testCustomer));
    }

    @Test
    public void invalidNullFieldCustomerCreation() {
        testCustomer.setFirstname(null);

        Assertions.assertFalse(this.customerDAO.createCustomer(testCustomer));
    }

    @Test
    public void validGetCustomerInformation() {
        Assertions.assertEquals("first_name", testCustomer.getFirstname());
        Assertions.assertEquals("last_name", testCustomer.getLastname());
        Assertions.assertEquals("gender", testCustomer.getGender());
        Assertions.assertEquals("address", testCustomer.getAddress());
        Assertions.assertEquals("dob", testCustomer.getDOB());
        Assertions.assertEquals("contact", testCustomer.getContact());
        Assertions.assertEquals("nic", testCustomer.getNIC());
        Assertions.assertEquals("passport", testCustomer.getPassport());
    }


    @Test
    public void invalidDAOInsertionError() {
// TODO check for sql exception handling
    }

    @AfterEach
    public void teardown() throws SQLException {
        PreparedStatement ps = this.con.prepareStatement("DELETE FROM customer"
                + " WHERE id = ?");
        ps.setString(1, customerID);
        ps.executeUpdate();

        this.con.close();
        this.con = null;
        this.customerDAO = null;
    }
}