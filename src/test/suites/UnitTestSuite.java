import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ CustomerTests.class, DatabaseTests.class, FlightTests.class,
                 TicketCTest.class, UserDAOTest.class, UserTest.class})
public class UnitTestSuite {
}