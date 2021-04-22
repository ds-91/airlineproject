import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightDAO {

    private DatabaseManager databaseManager;

    public FlightDAO() {
        this.databaseManager = new DatabaseManager();
    }

    public boolean createFlight(Flight flight) {
        if (flight == null) {
            return false;
        }

        //maybe there is a better way
        if (flight.getFlightname() == null || flight.getFlightname().isEmpty() ||
            flight.getSource() == null || flight.getSource().isEmpty() ||
            flight.getDepart() == null || flight.getDepart().isEmpty() ||
            flight.getDate() == null || flight.getDate().isEmpty() ||
            flight.getDeptime() == null || flight.getDeptime().isEmpty() ||
            flight.getArrtime() == null || flight.getArrtime().isEmpty() ||
            flight.getFlightcharge() == null || flight.getFlightcharge().isEmpty()) {

            return false;
        }

        Connection con = this.databaseManager.getDatabaseConnection();
        int success = 0;
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO "
                + "flight(flightname, source, depart, date, deptime, arrtime,"
                + "flightcharge) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, flight.getFlightname());
            pst.setString(2, flight.getSource());
            pst.setString(3, flight.getDepart());
            pst.setString(4, flight.getDate());
            pst.setString(5, flight.getDeptime());
            pst.setString(6, flight.getArrtime());
            pst.setString(7, flight.getFlightcharge());

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
            String sql = "SELECT MAX(id) FROM flight";
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
