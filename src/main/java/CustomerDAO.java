import jdk.internal.joptsimple.internal.Strings;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO {

    private final DatabaseManager databaseManager;
    private final Connection con;

    public CustomerDAO() {
        this.databaseManager = new DatabaseManager();
        this.con = this.databaseManager.getDatabaseConnection();
    }

    public int nextIntInDatabase() {
        Connection con = this.databaseManager.getDatabaseConnection();
        try {
            String sql = "SELECT MAX(id) FROM user";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt("MAX(id)");

        } catch (SQLException ex) {
            Logger.getLogger(userCreation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }


    public boolean createCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }

        String[] customerArray = customer.getCustomerArray();
        // check for null or empty in all strings
        for (String string :
                customerArray) {
            if (customer.getFirstname() == null || customer.getFirstname().isEmpty() ||
            customer.getLastname() == null || customer.getLastname().isEmpty() ||
            customer.getGender() == null || customer.getGender().isEmpty() ||
            customer.getAddress() == null || customer.getAddress().isEmpty() ||
            customer.getDOB() == null || customer.getDOB().isEmpty() ||
            customer.getContact() == null || customer.getContact().isEmpty() ||
            customer.getUserImage() == null || customer.getNIC() == null ||
            customer.getNIC().isEmpty() || customer.getPassport() == null ||
            customer.getPassport().isEmpty())
                return false;
             }

        int success = 0;
        try {
            PreparedStatement pst =
                    con.prepareStatement(
                            "insert into customer(firstname,lastname,nic,passport,address,dob,gender,contact,photo)values(?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, customer.getFirstname());
            pst.setString(2, customer.getLastname());
            pst.setString(3, customer.getNIC());
            pst.setString(4, customer.getPassport());
            pst.setString(5, customer.getAddress());
            pst.setString(6, customer.getDOB());
            pst.setString(7, customer.getGender());
            pst.setString(8, customer.getContact());
            pst.setBytes(9, customer.getUserImage());
            success = pst.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success > 0;
    }

    public boolean updateCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }

        String[] customerArray = customer.getCustomerArray();
        // check for null or empty in all strings
        for (String string :
                customerArray) {
            if (Strings.isNullOrEmpty(string))
                return false;
        }

        int success = 0;
        try {
            PreparedStatement pst =
                    con.prepareStatement(
                            "update customer set firstname = ?,lastname = ?,nic = ?,passport = ?,address= ?,dob = ?,gender = ?,contact = ?,photo = ? where id = ?");
            pst.setString(2, customer.getFirstname());
            pst.setString(3, customer.getLastname());
            pst.setString(4, customer.getNIC());
            pst.setString(5, customer.getPassport());
            pst.setString(6, customer.getAddress());
            pst.setString(7, customer.getDOB());
            pst.setString(8, customer.getGender());
            pst.setString(9, customer.getContact());
            pst.setBytes(10, customer.getUserImage());
            pst.setInt(1, customer.getID());

            success = pst.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success > 0;
    }

    public Customer searchCustomer(int id) {

        if (String.valueOf(id).isEmpty()) {
            return null;
        }

        try {
            PreparedStatement pst =
                    con.prepareStatement("select * from customer where id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next() == false) {
                return null;
            } else {
                String fname = rs.getString("firstname");
                String lname = rs.getString("lastname");
                String nic = rs.getString("nic");
                String passport = rs.getString("passport");

                String address = rs.getString("address");
                String dob = rs.getString("dob");
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                String gender = rs.getString("gender");

                Blob blob = rs.getBlob("photo");
                byte[] _imagebytes = blob.getBytes(1, (int) blob.length());

                String contact = rs.getString("contact");
                con.close();
                return new Customer(id, fname, lname, gender, address, dob, contact, _imagebytes, nic, passport);
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}