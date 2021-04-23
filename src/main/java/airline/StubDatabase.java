package airline;

/**
 * Part of integration tests. Stubbed database for when the database was not implemented.
 */
public class StubDatabase {
    String firstName;
    String lastName;
    String gender;

    /**
     * Inserts a new Customer object into the stubbed database.
     * @param customer
     */
    public void insertCustomer(Customer customer) {
        this.firstName = customer.getFirstname();
        this.lastName = customer.getLastname();
        this.gender = customer.getGender();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }
}
