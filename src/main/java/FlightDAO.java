import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FlightDAO {

    private DatabaseManager databaseManager;

    public FlightDAO() {
        this.databaseManager = new DatabaseManager();
    }

    public boolean createFlight(Flight flight) {
        if (flight == null || flight.checkForNullOrEmpty()) {
            return false;
        }

        Connection con = this.databaseManager.getDatabaseConnection();
        int success = 0;
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO "
                + "flight(id, flightname, source, depart, date, deptime, arrtime,"
                + "flightcharge) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, flight.getId());
            pst.setString(2, flight.getFlightname());
            pst.setString(3, flight.getSource());
            pst.setString(4, flight.getDepart());
            pst.setString(5, flight.getDate());
            pst.setString(6, flight.getDeptime());
            pst.setString(7, flight.getArrtime());
            pst.setString(8, flight.getFlightcharge());

            success = pst.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success > 0;
    }
}
