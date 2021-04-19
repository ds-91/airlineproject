import java.sql.Date;
import javax.swing.JButton;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class IntegrationTestSuite {
    @Mock
    private FlightDAO mockDAO;

    @InjectMocks
    private addflight addFlight;

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
        Mockito.verify(mockDAO).createFlight(Mockito.any(Flight.class));
    }

    @AfterEach
    public void teardown() {
        mockDAO = null;
        addFlight = null;
        flight = null;
    }

    @Test
    public void databaseStubTest()
    {
        byte [] userimage = new byte[]{};
        Customer customer = new Customer("01", "first_name",
            "last_name", "gender", "address",
            "dob", "contact", userimage, "nic", "passport");
        StubDatabase stubDatabase = new StubDatabase();
        stubDatabase.insertCustomer(customer);

        Assert.assertEquals("first_name", stubDatabase.getFirstName());
        Assert.assertEquals("last_name", stubDatabase.getLastName());
        Assert.assertEquals("gender", stubDatabase.getGender());
    }
}
