import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private DatabaseManager databaseManager;
    boolean success;


    public UserDAO() {
        this.databaseManager = new DatabaseManager();
    }

    public boolean insertNewUser(User user) {
        if (user == null) {
            return false;
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty() ||
                user.getLastName() == null || user.getLastName().isEmpty() ||
                user.getUsername() == null || user.getUsername().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            return false;
        }
        Connection con = this.databaseManager.getDatabaseConnection();
        try {
            String sql = "INSERT INTO USER (firstname, lastname, username, password) VALUES(?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());

            success = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
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
