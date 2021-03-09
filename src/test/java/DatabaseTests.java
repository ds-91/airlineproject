import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DatabaseTests {
  @Test
  public void testConnection() {
    Assertions.assertDoesNotThrow(() -> {
      DatabaseManager dm = new DatabaseManager();
    });
  }
}
