import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ticketreportTest {

    private ticketreport ticketreport;

    @BeforeEach
    public void setUp() {
        ticketreport = new ticketreport();
    }

    @AfterEach
    public void tearDown() {
        ticketreport = null;
    }

    @Test
    public void loadDataTest() {

        ticketreport.LoadData();

        // Check that LoadData() retrieves data from the ticket table
        Assertions.assertNotNull(ticketreport.rs);
    }

    @Test
    public void loadDataErrorTest() {

        // assert does not throw error
        assertDoesNotThrow(() -> ticketreport.LoadData());
    }
}
