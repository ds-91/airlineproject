import java.sql.*;

public class DatabaseManager {
  static final String DB_URL = "jdbc:mysql://localhost/airline";
  private Connection con;

  public DatabaseManager(Connection con) {
    this.con = con;
  }

  public DatabaseManager() throws SQLException {
    con = DriverManager.getConnection(DB_URL, "root", "password");
  }
}
