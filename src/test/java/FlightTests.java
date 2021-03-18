import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlightTests {

    private Connection con;
    private FlightDAO flightDAO;
    private Flight testFlight;

    @BeforeEach
    public void setup() {
        DatabaseManager databaseManager = new DatabaseManager();
        this.testFlight = new Flight("test_id", "test_name",
            "test_source", "test_depart", "test_date",
            "test_deptime", "test_arrtime", "test_flightcharge");
        this.con = databaseManager.getDatabaseConnection();
        this.flightDAO = new FlightDAO();
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
    public void invalidDAOInsertionError() {

    }

    @AfterEach
    public void teardown() throws SQLException {
        PreparedStatement ps = this.con.prepareStatement("DELETE FROM flight"
            + " WHERE id = ?");
        ps.setString(1, "test_id");
        ps.executeUpdate();

        this.con.close();
        this.con = null;
        this.flightDAO = null;
    }
}