import org.junit.Assert;
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

  @Test
  public void databaseStubTest()
  {
    byte [] userimage = new byte[]{};
    Customer customer = new Customer("01", "first_name",
            "last_name", "gender", "address",
            "dob", 1234567

            , userimage, "nic", "passport");
    StubDatabase stubDatabase = new StubDatabase();
    stubDatabase.insertCustomer(customer);

    Assert.assertEquals("first_name", stubDatabase.getFirstName());
    Assert.assertEquals("last_name", stubDatabase.getLastName());
    Assert.assertEquals("gender", stubDatabase.getGender());
  }
}
