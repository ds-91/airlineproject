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

    @BeforeEach
    public void setup() {
        DatabaseManager databaseManager = new DatabaseManager();
        this.con = databaseManager.getDatabaseConnection();
        this.flightDAO = new FlightDAO();
    }

    @Test
    public void validFlightCreation() {
        Flight testFlight = new Flight("test_id", "test_flightname",
            "test_source", "test_depart", "test_date",
            "test_deptime", "test_arrtime",
            "test_flightcharge");

        boolean success = this.flightDAO.createFlight(testFlight);

        Assertions.assertTrue(success);
    }

    @Test
    public void invalidNullFlightCreation() {
        boolean success = this.flightDAO.createFlight(null);

        Assertions.assertFalse(success);
    }

    @Test
    public void invalidMissingInfoFlightCreation() {
        Flight testFlight = new Flight(null, "", "", null,
            null, "", null, null);
        Flight emptyStringFlight = new Flight("test_id", "", "asdf", "asdf",
            "asdf", "asdf", "asdf", "asdf");
        Flight nullFieldFlight = new Flight("test_id", "", "asdf", "asdf",
            "asdf", "asdf", null, "asdf");

        Assertions.assertFalse(this.flightDAO.createFlight(testFlight));
        Assertions.assertFalse(this.flightDAO.createFlight(emptyStringFlight));
        Assertions.assertFalse(this.flightDAO.createFlight(nullFieldFlight));
    }

    @AfterEach
    public void teardown() {
        try {
            PreparedStatement ps = this.con.prepareStatement("DELETE FROM flight"
                + " WHERE id = ?");
            ps.setString(1, "test_id");
            ps.executeUpdate();

            this.con.close();
            this.con = null;
            this.flightDAO = null;
        } catch (SQLException e) {
            if (this.con == null) {
                System.out.println("Con was null when closing");
            } else {
                e.printStackTrace();
            }
        }
    }
}
