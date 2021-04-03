import java.sql.Date;
import javax.swing.JButton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MockFlightDAOTests {

    @Mock
    private FlightDAO mockDAO;

    @InjectMocks
    private addflight mockAddFlight;

    private Flight flight;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        flight = new Flight("test_id", "test_name",
            "test_source", "test_depart", "test_date",
            "test_deptime", "test_arrtime", "test_flightcharge");
    }

    @Test
    public void createValidFlightFromGUI() {
        JButton buttonAddFlight = mockAddFlight.getButtonAddFlight();

        mockAddFlight.getTxtflightname().setText("TEST_FLIGHT_NAME");
        mockAddFlight.getTxtdate().setDate(new Date(2021, 3, 30));
        mockAddFlight.getTxtdtime().setText("TEST_DEPART_TIME");
        mockAddFlight.getTxtarrtime().setText("TEST_ARRIVAL_TIME");
        mockAddFlight.getTxtflightcharge().setText("TEST_FLIGHT_CHARGE");
        mockAddFlight.getTxtsource().setSelectedIndex(0);
        mockAddFlight.getTxtdepart().setSelectedIndex(0);

        Mockito.when(mockDAO.createFlight(Mockito.any(Flight.class))).thenReturn(true);
        buttonAddFlight.doClick();
        Mockito.verify(mockDAO).createFlight(Mockito.any(Flight.class));
    }

    @AfterEach
    public void teardown() {
        mockDAO = null;
        mockAddFlight = null;
        flight = null;
    }
}