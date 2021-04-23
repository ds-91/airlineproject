import airline.DatabaseManager;
import airline.Flight;
import airline.FlightDAO;
import airline.Main;
import airline.addflight;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Collection of tests on the addflight.java form.
 * GUI Tests.
 */
public class AddFlightTests {

    private Connection con;
    private FlightDAO flightDAO;
    private Flight testFlight;
    private addflight testAddFlightGui;
    private FrameFixture window;


    /**
     * Runs before each test is run. Initializes a new database manager, connection,
     * flight, DAO, and addflight form.
     */
    @BeforeEach
    public void setup() {
        DatabaseManager databaseManager = new DatabaseManager();
        this.testFlight = new Flight("test_id", "test_name",
            "test_source", "test_depart", "test_date",
            "test_deptime", "test_arrtime", "test_flightcharge");
        this.con = databaseManager.getDatabaseConnection();
        this.flightDAO = new FlightDAO();
        this.testAddFlightGui = new addflight();

        Main frame = GuiActionRunner.execute(() -> new Main());
        window = new FrameFixture(frame);
    }

    /**
     * Tests if the addflight GUI is initialized properly.
     */
    @Test
    public void validInitGui() {
        Assertions.assertNotNull(testAddFlightGui);
    }

    /**
     * Tests if the 'Add Flight' button is accessible on the GUI.
     */
    @Test
    public void validGetButtonAddFlight() {
        Assertions.assertNotNull(testAddFlightGui.getButtonAddFlight());
    }

    /**
     * Tests if the 'Cancel' button is accessible on the GUI.
     */
    @Test
    public void validGetButtonCancel() {
        Assertions.assertNotNull(testAddFlightGui.getButtonCancel());
    }

    /**
     * Tests if the flight ID label is accessible on the GUI.
     */
    @Test
    public void validGetLabelFlightId() {
        Assertions.assertNotNull(testAddFlightGui.getLabelFlightId());
        Assertions.assertNotEquals("", testAddFlightGui.getLabelFlightId().getText());
    }

    /**
     * Tests if the arrival time text field is able to be edited.
     */
    @Test
    public void validGetTextArrivalTime() {
        testAddFlightGui.getTxtarrtime().setText("test_arrival_time");
        Assertions.assertEquals("test_arrival_time", testAddFlightGui.getTxtarrtime().getText());
    }

    /**
     * Tests if the flight date datepicker is able to be edited.
     */
    @Test
    public void validGetTextDate() {
        testAddFlightGui.getTxtdate().setDate(new Date(2021, 3, 30));
        Assertions.assertEquals(new Date(2021, 3, 30), testAddFlightGui.getTxtdate().getDate());
    }

    /**
     * Tests if the depart time text field is able to be edited.
     */
    @Test
    public void validGetTextDepartTime() {
        testAddFlightGui.getTxtdtime().setText("test_depart_time");
        Assertions.assertEquals("test_depart_time", testAddFlightGui.getTxtdtime().getText());
    }

    /**
     * Tests if the depart location drop down list is able to be selected.
     */
    @Test
    public void validGetTextDepartLocation() {
        testAddFlightGui.getTxtdepart().setSelectedIndex(3);
        Assertions.assertEquals(3, testAddFlightGui.getTxtdepart().getSelectedIndex());
    }

    /**
     * Tests if the flight charge text field is able to be edited.
     */
    @Test
    public void validGetTextFlightCharge() {
        testAddFlightGui.getTxtflightcharge().setText("100");
        Assertions.assertEquals("100", testAddFlightGui.getTxtflightcharge().getText());
    }

    /**
     * Tests if the flight ID label is accessible on the GUI.
     */
    @Test
    public void validGetTextFlightId() {
        Assertions.assertNotNull(testAddFlightGui.getTxtflightid());
        Assertions.assertNotEquals("", testAddFlightGui.getTxtflightid().getText());
    }

    /**
     * Tests if the flight name text field is able to be edited.
     */
    @Test
    public void validGetTextFlightName() {
        testAddFlightGui.getTxtflightname().setText("test_flight");
        Assertions.assertEquals("test_flight", testAddFlightGui.getTxtflightname().getText());
    }

    /**
     * Tests if the flight source drop down is able to be selected.
     */
    @Test
    public void validGetTextSource() {
        testAddFlightGui.getTxtsource().setSelectedIndex(3);
        Assertions.assertEquals(3, testAddFlightGui.getTxtsource().getSelectedIndex());
    }

    /**
     * Tests if the cancel button is able to be clicked.
     */
    @Test
    public void validCancel() {
        testAddFlightGui.setVisible(true);
        testAddFlightGui.getButtonCancel().doClick();
        Assertions.assertFalse(testAddFlightGui.isVisible());
    }

    /**
     * Tests if the GUI successfully adds a new flight given all fields input.
     */
    @Test
    public void validAddNewFlight() {
        testAddFlightGui.getTxtflightname().setText("TEST_FLIGHT_NAME");
        testAddFlightGui.getTxtdate().setDate(new Date(2021, 3, 30));
        testAddFlightGui.getTxtdtime().setText("TEST_DEPART_TIME");
        testAddFlightGui.getTxtarrtime().setText("TEST_ARRIVAL_TIME");
        testAddFlightGui.getTxtflightcharge().setText("TEST_FLIGHT_CHARGE");
        testAddFlightGui.getTxtsource().setSelectedIndex(0);
        testAddFlightGui.getTxtdepart().setSelectedIndex(0);

        testAddFlightGui.getButtonAddFlight().doClick();
        Assertions.assertEquals("Flight created!", testAddFlightGui.getSuccessPane().getMessage().toString());
    }

    /**
     * Tests if the add flight button doesn't create a new flight when clicked.
     */
    @Test
    public void invalidAddNewFlight() {
        testAddFlightGui.getTxtdate().setDate(new Date(2021, 3, 30));
        testAddFlightGui.getTxtdtime().setText("TEST_DEPART_TIME");
        testAddFlightGui.getTxtarrtime().setText("TEST_ARRIVAL_TIME");
        testAddFlightGui.getTxtflightcharge().setText("TEST_FLIGHT_CHARGE");
        testAddFlightGui.getTxtsource().setSelectedIndex(0);
        testAddFlightGui.getTxtdepart().setSelectedIndex(0);

        testAddFlightGui.getButtonAddFlight().doClick();
        Assertions.assertEquals("Error creating flight!", testAddFlightGui.getSuccessPane().getMessage().toString());
    }

    /**
     * Tests if the flight name text field is editable.
     */
    @Test
    public void canEditFlightName() {
        window.show();
        window.menuItem("flightMenu").click();
        window.menuItem("addFlightMenuItem").click();

        window.textBox("txtflightname").setText("test_flight");
        Assertions.assertEquals("test_flight", window.textBox("txtflightname").text());
    }

    /**
     * Tests if the depart time text field is editable.
     */
    @Test
    public void canEditDepartTime() {
        window.show();
        window.menuItem("flightMenu").click();
        window.menuItem("addFlightMenuItem").click();

        window.textBox("txtdtime").setText("test_depttime");
        Assertions.assertEquals("test_depttime", window.textBox("txtdtime").text());
    }

    /**
     * Tests if the arrival time text field is editable.
     */
    @Test
    public void canEditArrivalTime() {
        window.show();
        window.menuItem("flightMenu").click();
        window.menuItem("addFlightMenuItem").click();

        window.textBox("txtarrtime").setText("test_arrtime");
        Assertions.assertEquals("test_arrtime", window.textBox("txtarrtime").text());
    }

    /**
     * Tests if the flight charge text field is editable.
     */
    @Test
    public void canEditFlightCharge() {
        window.show();
        window.menuItem("flightMenu").click();
        window.menuItem("addFlightMenuItem").click();

        window.textBox("txtflightcharge").setText("test_charge");
        Assertions.assertEquals("test_charge", window.textBox("txtflightcharge").text());
    }

    /**
     * Ran after each test. Frees up memory from previously created objects.
     */
    @AfterEach
    public void teardown() throws SQLException {
        window.cleanUp();
        this.con.close();
        this.con = null;
        this.flightDAO = null;
        this.testFlight = null;
    }
}
