import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private DatabaseManager databaseManager;
    private Connection con = databaseManager.getDatabaseConnection();

    public UserDAO() throws SQLException {
        this.databaseManager = new DatabaseManager();
    }

    //Using ID is ignored because it is auto genereated
    public void insertNewUser(User user) {
        try {
            String sql = "INSERT INTO USER VALUES(?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //To find a unique user and verify they are unique when creating new users
    public boolean userExists(User user) {
        try {
            String sql = "SELECT * FROM USER WHERE USERNAME = ? and PASSWORD = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
