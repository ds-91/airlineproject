import airline.Customer;
import airline.CustomerDAO;
import airline.DatabaseManager;
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
                "dob", 1234567, userimage, "nic", "passport");
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
        Assertions.assertEquals(1234567, testCustomer.getContact());
        Assertions.assertEquals("nic", testCustomer.getNIC());
        Assertions.assertEquals("passport", testCustomer.getPassport());
    }

    @Test
    public void validCustomerUpdateNoGUI() {
        Customer custUpdate = this.testCustomer;
        custUpdate.setID("CS003");
        custUpdate.setFirstname("Update");
        boolean success = this.customerDAO.updateCustomer(custUpdate);
        Assertions.assertTrue(success);
    }

    @Test
    public void customerSearchNoGUI() {
        Customer custSearch = this.customerDAO.searchCustomer("CS001");
        Assertions.assertTrue(custSearch.getFirstname().equals("john"));
    }

    @Test
    public void validAutoID() {
        byte [] userimage = new byte[]{};
        String id1 = customerDAO.autoID();
        Customer testCustomer1 = new Customer(id1, "first_name",
                "last_name", "gender", "address",
                "dob", 4, userimage, "nic", "passport");
        this.customerDAO.createCustomer(testCustomer1);
        String id2 = customerDAO.autoID();
        Customer testCustomer2 = new Customer(id1, "first_name",
                "last_name", "gender", "address",
                "dob", 5, userimage, "nic", "passport");

        Assertions.assertFalse(id1.equals(id2));
    }

    @Test
    public void changeCustomerInfo() {
        Customer custChange = this.testCustomer;

        String firstname = "changeFirst";
        String lastname= "changeLast";
        String gender= "changeGender";
        String address= "changeAddress";
        String dob= "changeDOB";
        Integer contact = 911;
        String nic = "80085";
        String passport = "changePassport";

        custChange.setFirstname(firstname);
        custChange.setLastname(lastname);
        custChange.setGender(gender);
        custChange.setAddress(address);
        custChange.setDOB(dob);
        custChange.setContact(contact);
        custChange.setNIC(nic);
        custChange.setPassport(passport);

        Assertions.assertTrue(custChange.getFirstname().equals(firstname) && custChange.getLastname().equals(lastname)
                && custChange.getGender().equals(gender)&&custChange.getAddress().equals(address)&&custChange.getDOB().equals(dob)&&custChange.getContact().equals(contact)
                &&custChange.getNIC().equals(nic)&&custChange.getPassport().equals(passport));
    }

    @Test
    public void changeCustomerImage() {
        byte [] userimage = new byte[]{};
        byte [] userimageNew = new byte[]{-128,0,1,2,3,4,5,127};

        this.testCustomer.setUserImage(userimageNew);

        Assertions.assertTrue(!testCustomer.getUserImage().equals(userimage));
    }

    @Test
    public void changeCustomerID() {
        String OGID = "CS998";
        String IDchange = "CS999";
        Customer custChange = new Customer(OGID);

        custChange.setID(IDchange);

        Assertions.assertTrue(!custChange.getID().equals(OGID));
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