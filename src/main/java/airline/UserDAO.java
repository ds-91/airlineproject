package airline;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private DatabaseManager databaseManager;
    private Connection con;

    public UserDAO() {
        this.databaseManager = new DatabaseManager();
        this.con  = databaseManager.getDatabaseConnection();
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
        try {
            String sql = "INSERT INTO USER VALUES(?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());

            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String nextIdInDatabase() {
        try {

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select MAX(id) from user");
            rs.next();
            rs.getString("MAX(id)");
            if (rs.getString("MAX(id)") == null) {
                return "UO001";
            } else {
                long id =
                        Long.parseLong(rs.getString("MAX(id)").substring(2, rs.getString("MAX(id)").length()));
                id++;
                return "UO" + String.format("%03d", id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }


}
