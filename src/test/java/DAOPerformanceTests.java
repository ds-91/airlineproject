import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DAOPerformanceTests {

    private FlightDAO flightDAO;
    private Flight testFlight;
    private Connection con;
    private DatabaseManager databaseManager;

    @BeforeEach
    public void setup() {
        flightDAO = new FlightDAO();
        databaseManager = new DatabaseManager();
        con = databaseManager.getDatabaseConnection();
        testFlight = new Flight("3252352", "test_name",
            "test_source", "test_depart", "test_date",
            "test_deptime", "test_arrtime", "test_flightcharge");
    }

    @Test
    public void flightDAOAccessTime() {
        long begin = System.currentTimeMillis();
        flightDAO.createFlight(testFlight);
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
    }
}
