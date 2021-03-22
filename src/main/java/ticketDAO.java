import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ticketDAO {

    private DatabaseManager databaseManager;

    public ticketDAO() throws SQLException {
        this.databaseManager = new DatabaseManager();

    }

    public boolean ticketC (ticketC ticket) {
        if (ticket == null || ticket.checkForNullOrEmpty()){
            return false;
        }

        Connection con = this.databaseManager.getDatabaseConnection();
        int success = 0;
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO" + "ticketC(id,flightid, custid, classes, " +
                    "price, seats, date) VALUES (?,?,?,?,?,?,?)");
            pst.setString(1, ticketC.id());
            pst.setString(2, ticketC.flightid());
            pst.setString(3, ticketC.custid());
            pst.setString(4, ticketC.classes());
            pst.setString(5, ticketC.price());
            pst.setString(6, ticketC.seats());
            pst.setString(7, ticketC.date());

            success = pst.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success > 0;
    }
}
