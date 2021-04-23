import com.mysql.cj.util.StringUtils;
import jdk.internal.joptsimple.internal.Strings;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerDAO {

    private DatabaseManager databaseManager;
    private StringUtils utils;
    private Connection con;

    public CustomerDAO() {
        this.databaseManager = new DatabaseManager();
        this.con = this.databaseManager.getDatabaseConnection();
    }

    public String autoID() {
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select MAX(id) from customer");
            rs.next();
            rs.getString("MAX(id)");
            if (rs.getString("MAX(id)") == null) {
                return "CS001";
            } else {
                long id =
                        Long.parseLong(rs.getString("MAX(id)").substring(2, rs.getString("MAX(id)").length()));
                id++;
                return "CS" + String.format("%03d", id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public boolean createCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }

        String[] customerArray = customer.getCustomerArray();
        // check for null or empty in all strings
        for (String string :
                customerArray) {
            if (StringUtils.isNullOrEmpty(string))
                return false;
             }

        int success = 0;
        try {
            PreparedStatement pst =
                    con.prepareStatement(
                            "insert into customer(id,firstname,lastname,nic,passport,address,dob,gender,contact,photo)values(?,?,?,?,?,?,?,?,?,?)");

            pst.setString(1, customer.getID());
            pst.setString(2, customer.getFirstname());
            pst.setString(3, customer.getLastname());
            pst.setString(4, customer.getNIC());
            pst.setString(5, customer.getPassport());
            pst.setString(6, customer.getAddress());
            pst.setString(7, customer.getDOB());
            pst.setString(8, customer.getGender());
            pst.setInt(9, customer.getContact());
            pst.setBytes(10, customer.getUserImage());
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

        // check for null or empty in all strings
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
            pst.setInt(9, customer.getContact());
            pst.setBytes(10, customer.getUserImage());
            pst.setString(1, customer.getID());

            success = pst.executeUpdate();

            con.close();
            return success > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return success > 0;
    }

    public Customer searchCustomer(String id) {
        try {
            PreparedStatement pst =
                    con.prepareStatement("select * from customer where id = ?");
            pst.setString(1, id);
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


                Integer contact = rs.getInt("contact");
                con.close();
                return new Customer(id, fname, lname, gender, address, dob, contact, _imagebytes, nic, passport);
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}