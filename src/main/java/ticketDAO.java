import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ticketDAO {

    private DatabaseManager databaseManager;

    public ticketDAO() throws SQLException {
        this.databaseManager = new DatabaseManager();

    }

    public boolean ticketC (ticketC ticket) {
        if (ticket == null){
            return false;
        }

        if (ticket.getId() == null || ticket.getId().isEmpty() ||
                ticket.getId() == null || ticket.getId().isEmpty() ||
                ticket.getFlightid() == null || ticket.getFlightid().isEmpty() ||
                ticket.getCustid() == null || ticket.getCustid().isEmpty() ||
                ticket.getClasses() == null || ticket.getClasses().isEmpty() ||
                ticket.getPrice() == null || ticket.getPrice().isEmpty() ||
                ticket.getSeats() == null || ticket.getSeats().isEmpty() ||
                ticket.getDate() == null || ticket.getDate().isEmpty()) {

            return false;
        }

        Connection con = this.databaseManager.getDatabaseConnection();
        int success = 0;
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO" + "ticket(id,flightid, custid, classes, " +
                    "price, seats, date) VALUES (?,?,?,?,?,?,?)");
            pst.setString(1, ticket.getId());
            pst.setString(2, ticket.getFlightid());
            pst.setString(3, ticket.getCustid());
            pst.setString(4, ticket.getClasses());
            pst.setString(5, ticket.getPrice());
            pst.setString(6, ticket.getSeats());
            pst.setString(7, ticket.getDate());

            success = pst.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success > 0;
    }
}
