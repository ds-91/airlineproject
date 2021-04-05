import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseTests {

  private DatabaseManager dbm;

  @Test
  public void testValidConnection() {
    Assertions.assertDoesNotThrow(() -> {
      dbm = new DatabaseManager();
    });
  }
}
