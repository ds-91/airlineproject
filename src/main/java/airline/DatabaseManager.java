package airline;

import java.sql.*;

/**
 * Class that manages a connection to the application's database.
 */
public class DatabaseManager {

  static final String DB_URL = "jdbc:mysql://localhost/airlineproject";

  private Connection con;

  /**
   * Constructor that initializes the connection to the database.
   */
  public DatabaseManager() {
    try {
      con = DriverManager.getConnection(DB_URL, "root", "marko");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the database connection
   * @return con
   */
  public Connection getDatabaseConnection() {
    return con;
  }
}