import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PerformanceTests {

    private FlightDAO flightDAO;
    private ticketDAO ticketDAO;
    private CustomerDAO customerDAO;
    private String customerID;

    private Flight testFlight;
    private ticketC testTicket;
    private Customer testCustomer;

    private Connection con;
    private DatabaseManager databaseManager;
    private ticketreport ticketReport;

    @BeforeEach
    public void setup() {
        flightDAO = new FlightDAO();
        ticketDAO = new ticketDAO();
        customerDAO = new CustomerDAO();
        ticketReport = new ticketreport();
        databaseManager = new DatabaseManager();
        con = databaseManager.getDatabaseConnection();
        testFlight = new Flight("3252352", "test_name",
                "test_source", "test_depart", "test_date",
                "test_deptime", "test_arrtime", "test_flightcharge");
        testTicket = new ticketC("3423423423", "test_flightid",
                "test_custid", "test_classes", "test_price",
                "test_seats", "test_date");
        customerID = customerDAO.autoID();
        byte [] userimage = new byte[]{};
        this.testCustomer = new Customer(customerID, "test_first_name",
                "test_last_name", "test_gender", "test_address",
                "test_dob", 1234567, userimage, "test_nic", "test_passport");
    }

    @Test
    public void flightDAOAccessTime() {
        long begin = System.currentTimeMillis();
        flightDAO.createFlight(testFlight);
        long end = System.currentTimeMillis();

        long elapsed = end - begin;

        assertTrue(elapsed < 100);
    }

    @Test
    public void ticketDAOAccessTime() {
        long begin = System.currentTimeMillis();
        ticketDAO.createTicket(testTicket);
        long end = System.currentTimeMillis();

        long elapsed = end - begin;

        assertTrue(elapsed < 100);
    }

    @Test
    public void ticketReportDataTime() {
        long begin = System.currentTimeMillis();
        ticketReport.LoadData();
        long end = System.currentTimeMillis();

        long elapsed = end - begin;

        assertTrue(elapsed < 100);
    }

    @Test
    public void customerDAOAccessTime() {
        long begin = System.currentTimeMillis();
        customerDAO.createCustomer(testCustomer);
        long end = System.currentTimeMillis();

        long elapsed = end - begin;

        assertTrue(elapsed < 100);
    }

    @Test
    public void customerDAOSearchTime() {
        long begin = System.currentTimeMillis();
        customerDAO.searchCustomer("CS001");
        long end = System.currentTimeMillis();

        long elapsed = end - begin;

        assertTrue(elapsed < 100);
    }

    @Test
    public void databaseConnectionTime() {
        long begin = System.currentTimeMillis();
        con = databaseManager.getDatabaseConnection();
        long end = System.currentTimeMillis();

        long elapsed = end - begin;

        assertTrue(elapsed < 100);
    }

    @AfterEach
    public void teardown() throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM flight"
                + " WHERE flightname = ?");
        ps.setString(1, "test_id");
        ps.executeUpdate();

        PreparedStatement psCust = con.prepareStatement("DELETE FROM customer"
                + " WHERE firstname = ?");
        psCust.setString(1, "test_first_name");
        psCust.executeUpdate();
    }
}
