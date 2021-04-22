import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ticketDAO {

    private DatabaseManager databaseManager;

    public ticketDAO() {
        this.databaseManager = new DatabaseManager();
    }

    public boolean createTicket (ticketC ticket) {
        if (ticket == null){
            return false;
        }

        if (ticket.getFlightid() == null || ticket.getFlightid().isEmpty() ||
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
            PreparedStatement pst = con.prepareStatement("INSERT INTO " +
                "ticket(flightid, custid, classes, " +
                    "price, seats, date) VALUES (?,?,?,?,?,?)");
            pst.setString(1, ticket.getFlightid());
            pst.setString(2, ticket.getCustid());
            pst.setString(3, ticket.getClasses());
            pst.setString(4, ticket.getPrice());
            pst.setString(5, ticket.getSeats());
            pst.setString(6, ticket.getDate());

            success = pst.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success > 0;
    }

    public int nextIdInDatabase() {
        Connection con = this.databaseManager.getDatabaseConnection();
        try {
            String sql = "SELECT MAX(id) FROM user";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt("MAX(id)") + 1;

        } catch (SQLException ex) {
            Logger.getLogger(userCreation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
