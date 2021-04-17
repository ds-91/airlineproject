import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketCTest {

    private Connection con;
    private ticketDAO ticketDAO;
    private ticketC testTicket;

    @BeforeEach
    public void setup() throws SQLException {
        DatabaseManager databaseManager = new DatabaseManager();
        this.testTicket = new ticketC("test_id", "test_flightid",
                "test_custid", "test_classes", "test_price",
                "test_seats", "test_date");
        this.con = databaseManager.getDatabaseConnection();
        this.ticketDAO = new ticketDAO();
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

    @AfterEach
    public void teardown() throws SQLException {
        PreparedStatement ps = this.con.prepareStatement("DELETE FROM ticket"
                + " WHERE id = ?");
        ps.setString(1, "test_id");
        ps.executeUpdate();

        this.con.close();
        this.con = null;
        this.ticketDAO = null;
        this.testTicket = null;
    }
}
