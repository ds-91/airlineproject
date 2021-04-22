import java.awt.*;

/**
 * The Customer class models an airline customer.
 */
public class Customer {
    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    private String address;
    private String dob;
    private String contact;
    private byte[] userimage;
    private String nic;
    private String passport;


    /** Constructs a Customer instance with related values*/
    public Customer(){
    }
    public Customer(int id){
        this.id = id;
    }
    public Customer(int id, String firstname, String lastname, String gender, String address, String dob, String contact, byte[] userimage, String nic, String passport) {  // 1st (default) constructor
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.contact = contact;
        this.userimage = userimage;
        this.nic = nic;
        this.passport = passport;
    }

    /** Getters */
    public String[] getCustomerArray(){
        String[] array = {String.valueOf(this.id), this.firstname,  this.lastname,this.gender, this.address,this.dob, this.contact,this.nic, this.passport};
        return  array;
    }
    public int getID() {
        return this.id;
    }
    public String getNIC() {
        return this.nic;
    }

    public String getPassport() {
        return this.passport;
    }
    public String getFirstname(){
        return this.firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public String getGender(){
        return this.gender;
    }

    public String getAddress(){
        return this.address;
    }

    public String getContact(){
        return this.contact;
    }
    public String getDOB(){
        return this.dob;
    }

    public byte[] getUserImage(){
        return this.userimage;
    }

    /** Setters */

    public void setID(int id) {
        // validation here ?
       this.id = id;
    }
    public void setNIC(String nic) {
        this.nic = nic;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setContact(String contact){
        this.contact = contact;
    }
    public void setDOB(String dob){
        this.dob = dob;
    }

    public void setUserImage(byte[] userimage){
        this.userimage = userimage;
    }

}