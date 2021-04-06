public class StubDatabase {
    String firstName;
    String lastName;
    String gender;

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
