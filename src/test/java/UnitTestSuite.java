import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitTestSuite {

    private addflight testAddFlightGui;
    private FlightDAO flightDAO;
    private CustomerDAO customerDAO;
    private Customer testCustomer;
    private String customerID;
    private Connection con;
    private DatabaseManager databaseManager;
    private Flight testFlight;
    private ticketDAO ticketDAO;
    private ticketC testTicket;

    @BeforeEach
    public void setup() {
        this.databaseManager = new DatabaseManager();
        this.testAddFlightGui = new addflight();
        this.flightDAO = new FlightDAO();
        this.con = databaseManager.getDatabaseConnection();
        this.customerDAO = new CustomerDAO();
        byte [] userimage = new byte[]{};
        this.customerID = customerDAO.autoID();
        this.testFlight = new Flight("test_id", "test_name",
            "test_source", "test_depart", "test_date",
            "test_deptime", "test_arrtime", "test_flightcharge");
        this.testCustomer = new Customer(customerID, "first_name",
            "last_name", "gender", "address",
            "dob", "contact", userimage, "nic", "passport");
        this.testTicket = new ticketC("test_id", "test_flightid",
            "test_custid", "test_classes", "test_price",
            "test_seats", "test_date");
        this.con = databaseManager.getDatabaseConnection();
        this.ticketDAO = new ticketDAO();
    }

    @Test
    public void validInitGui() {
        Assertions.assertNotNull(testAddFlightGui);
    }

    @Test
    public void validGetButtonAddFlight() {
        Assertions.assertNotNull(testAddFlightGui.getButtonAddFlight());
    }

    @Test
    public void validGetButtonCancel() {
        Assertions.assertNotNull(testAddFlightGui.getButtonCancel());
    }

    @Test
    public void validGetLabelFlightId() {
        Assertions.assertNotNull(testAddFlightGui.getLabelFlightId());
        Assertions.assertNotEquals("", testAddFlightGui.getLabelFlightId().getText());
    }

    @Test
    public void validGetTextArrivalTime() {
        testAddFlightGui.getTxtarrtime().setText("test_arrival_time");
        Assertions.assertEquals("test_arrival_time", testAddFlightGui.getTxtarrtime().getText());
    }

    @Test
    public void validGetTextDate() {
        testAddFlightGui.getTxtdate().setDate(new Date(2021, 3, 30));
        Assertions.assertEquals(new Date(2021, 3, 30), testAddFlightGui.getTxtdate().getDate());
    }

    @Test
    public void validGetTextDepartTime() {
        testAddFlightGui.getTxtdtime().setText("test_depart_time");
        Assertions.assertEquals("test_depart_time", testAddFlightGui.getTxtdtime().getText());
    }

    @Test
    public void validGetTextDepartLocation() {
        testAddFlightGui.getTxtdepart().setSelectedIndex(3);
        Assertions.assertEquals(3, testAddFlightGui.getTxtdepart().getSelectedIndex());
    }

    @Test
    public void validGetTextFlightCharge() {
        testAddFlightGui.getTxtflightcharge().setText("100");
        Assertions.assertEquals("100", testAddFlightGui.getTxtflightcharge().getText());
    }

    @Test
    public void validGetTextFlightId() {
        Assertions.assertNotNull(testAddFlightGui.getTxtflightid());
        Assertions.assertNotEquals("", testAddFlightGui.getTxtflightid().getText());
    }

    @Test
    public void validGetTextFlightName() {
        testAddFlightGui.getTxtflightname().setText("test_flight");
        Assertions.assertEquals("test_flight", testAddFlightGui.getTxtflightname().getText());
    }

    @Test
    public void validGetTextSource() {
        testAddFlightGui.getTxtsource().setSelectedIndex(3);
        Assertions.assertEquals(3, testAddFlightGui.getTxtsource().getSelectedIndex());
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
    public void testValidConnection() {
        Assertions.assertDoesNotThrow(() -> {
            databaseManager = new DatabaseManager();
        });
    }

    @Test
    public void validFlightCreation() {
        boolean success = this.flightDAO.createFlight(testFlight);

        Assertions.assertTrue(success);
    }

    @Test
    public void invalidNullFlightCreation() {
        boolean success = this.flightDAO.createFlight(null);

        Assertions.assertFalse(success);
    }

    @Test
    public void invalidEmptyFieldFlightCreation() {
        testFlight.setFlightname("");

        Assertions.assertFalse(this.flightDAO.createFlight(testFlight));
    }

    @Test
    public void invalidNullFieldFlightCreation() {
        testFlight.setSource(null);

        Assertions.assertFalse(this.flightDAO.createFlight(testFlight));
    }

    @Test
    public void validGetFlightInformation() {
        Assertions.assertEquals("test_id", testFlight.getId());
        Assertions.assertEquals("test_name", testFlight.getFlightname());
        Assertions.assertEquals("test_source", testFlight.getSource());
        Assertions.assertEquals("test_depart", testFlight.getDepart());
        Assertions.assertEquals("test_date", testFlight.getDate());
        Assertions.assertEquals("test_deptime", testFlight.getDeptime());
        Assertions.assertEquals("test_arrtime", testFlight.getArrtime());
        Assertions.assertEquals("test_flightcharge", testFlight.getFlightcharge());
    }

    @Test
    public void validSetFlightInformation() {
        testFlight.setId("edited_id");
        testFlight.setFlightname("edited_name");
        testFlight.setSource("edited_source");
        testFlight.setDepart("edited_depart");
        testFlight.setDate("edited_date");
        testFlight.setDeptime("edited_deptime");
        testFlight.setArrtime("edited_arrtime");
        testFlight.setFlightcharge("edited_flightcharge");

        Assertions.assertEquals("edited_id", testFlight.getId());
        Assertions.assertEquals("edited_name", testFlight.getFlightname());
        Assertions.assertEquals("edited_source", testFlight.getSource());
        Assertions.assertEquals("edited_depart", testFlight.getDepart());
        Assertions.assertEquals("edited_date", testFlight.getDate());
        Assertions.assertEquals("edited_deptime", testFlight.getDeptime());
        Assertions.assertEquals("edited_arrtime", testFlight.getArrtime());
        Assertions.assertEquals("edited_flightcharge", testFlight.getFlightcharge());
    }

    @Test
    public void validTicketCreation() {
        boolean success = this.ticketDAO.createTicket(testTicket);

        Assertions.assertTrue(success);
    }

    @Test
    public void invalidNullTicketCreation() {
        boolean success = this.ticketDAO.createTicket(null);

        Assertions.assertFalse(success);
    }

    @Test
    public void invalidEmptyFieldTicketCreation() {
        testTicket.setFlightid("");

        Assertions.assertFalse(this.ticketDAO.createTicket(testTicket));
    }

    @Test
    public void invalidNullFieldTicketCreation() {
        testTicket.setCustid(null);

        Assertions.assertFalse(this.ticketDAO.createTicket(testTicket));
    }

    @Test
    public void validGetTicketInformation() {
        Assertions.assertEquals("test_id", testTicket.getId());
        Assertions.assertEquals("test_flightid", testTicket.getFlightid());
        Assertions.assertEquals("test_custid", testTicket.getCustid());
        Assertions.assertEquals("test_classes", testTicket.getClasses());
        Assertions.assertEquals("test_price", testTicket.getPrice());
        Assertions.assertEquals("test_seats", testTicket.getSeats());
        Assertions.assertEquals("test_date", testTicket.getDate());

    }

    @Test
    public void validSetTicketInformation() {
        testTicket.setId("edited_id");
        testTicket.setFlightid("edited_flightid");
        testTicket.setCustid("edited_custid");
        testTicket.setClasses("edited_classes");
        testTicket.setPrice("edited_price");
        testTicket.setSeats("edited_seats");
        testTicket.setDate("edited_date");

        Assertions.assertEquals("edited_id", testTicket.getId());
        Assertions.assertEquals("edited_flightid", testTicket.getFlightid());
        Assertions.assertEquals("edited_custid", testTicket.getCustid());
        Assertions.assertEquals("edited_classes", testTicket.getClasses());
        Assertions.assertEquals("edited_price", testTicket.getPrice());
        Assertions.assertEquals("edited_seats", testTicket.getSeats());
        Assertions.assertEquals("edited_date", testTicket.getDate());

    }

    @Test
    public void firstNameValid() {
        User user = new User(1, "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getFirstName());
        Assertions.assertNotEquals(null, user.getFirstName());
    }

    @Test
    public void lastNameValid() {
        User user = new User(1, "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getLastName());
        Assertions.assertNotEquals(null, user.getLastName());
    }

    @Test
    public void usernameValid() {
        User user = new User(1, "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getUsername());
        Assertions.assertNotEquals(null, user.getUsername());
    }

    @Test
    public void passwordValid() {
        User user = new User(1, "firstName", "lastName", "username", "password");

        Assertions.assertNotEquals("", user.getPassword());
        Assertions.assertNotEquals(null, user.getPassword());
    }

    @Test
    public void firstNameValidTwo() {
        User user = new User(1, "firstName", "lastName", "username", "password");

        Assertions.assertEquals("firstName", user.getFirstName());
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
        this.con = null;
    }

}
