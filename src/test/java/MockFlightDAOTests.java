import airline.Flight;
import airline.FlightDAO;
import airline.addflight;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Date;
import javax.swing.JButton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Integration tests using mocks to test the addflight GUI.
 */
public class MockFlightDAOTests {

    @Mock
    private FlightDAO mockDAO;

    @InjectMocks
    private addflight addFlight;

    private Flight flight;
    private Robot robot;

    /**
     * Runs before each test to initialize mocks and to create a new flight object.
     * @throws AWTException
     */
    @BeforeEach
    public void setup() throws AWTException {
        MockitoAnnotations.initMocks(this);
        flight = new Flight("test_id", "test_name",
            "test_source", "test_depart", "test_date",
            "test_deptime", "test_arrtime", "test_flightcharge");
        robot = new Robot();
    }

    /**
     * Tests if the flight DAO inserts a flight into the database using a mocked
     * DAO.
     */
    @Test
    public void createValidFlightFromGUI() {
        JButton buttonAddFlight = addFlight.getButtonAddFlight();

        addFlight.getTxtflightname().setText("TEST_FLIGHT_NAME");
        addFlight.getTxtdate().setDate(new Date(2021, 3, 30));
        addFlight.getTxtdtime().setText("TEST_DEPART_TIME");
        addFlight.getTxtarrtime().setText("TEST_ARRIVAL_TIME");
        addFlight.getTxtflightcharge().setText("TEST_FLIGHT_CHARGE");
        addFlight.getTxtsource().setSelectedIndex(0);
        addFlight.getTxtdepart().setSelectedIndex(0);

        Mockito.when(mockDAO.createFlight(Mockito.any(Flight.class))).thenReturn(true);
        buttonAddFlight.doClick();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Mockito.verify(mockDAO).createFlight(Mockito.any(Flight.class));
    }

    /**
     * Runs after each test to free up memory.
     */
    @AfterEach
    public void teardown() {
        mockDAO = null;
        addFlight = null;
        flight = null;
    }
}
