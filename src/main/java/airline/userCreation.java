package airline;

import javax.swing.*;
import java.sql.Connection;

public class userCreation extends javax.swing.JInternalFrame {

  private javax.swing.JTextField txtfirstname;
  private javax.swing.JTextField txtlastname;
  private javax.swing.JPasswordField txtpassword;
  private javax.swing.JTextField txtusername;
  private javax.swing.JButton buttonAddUser;
  private javax.swing.JButton buttonCancel;
  private javax.swing.JLabel labelUserId;
  private javax.swing.JLabel labelUserFirstName;
  private javax.swing.JLabel labelUserLastName;
  private javax.swing.JLabel labelUsername;
  private javax.swing.JLabel labelPassword;
  private javax.swing.JPanel jPanel1;

  private javax.swing.JLabel txtuserid;

  private javax.swing.JLabel status;


  private final JOptionPane successPane = new JOptionPane();

  UserDAO userDAO;
  User user;
  String id;
  String firstName;
  String lastName;
  String username;
  String password;

  /**
   * Creates new form, userCreation. This will be the window created for the user interface.
   **/


  public userCreation() {
    initComponents();
    this.userDAO = new UserDAO();
    id = userDAO.nextIdInDatabase();
    txtuserid.setText(id);
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   * Sets all the labels and text fields within the window to their appropriate String.
   *
   * Includes lambda to insert user into the database.
   */
  @SuppressWarnings("unchecked")
  private void initComponents() {
    buttonAddUser = new JButton();
    jPanel1 = new JPanel();
    jPanel1.setName("userCreationPanel");
    labelUserId = new JLabel();
    labelUserFirstName = new JLabel();
    labelUserLastName = new JLabel();
    labelUsername = new JLabel();
    labelPassword = new JLabel();
    txtuserid = new JLabel();
    txtuserid.setName("textUserId");
    txtfirstname = new javax.swing.JTextField();
    txtfirstname.setName("textFirstName");
    txtlastname = new javax.swing.JTextField();
    txtlastname.setName("textLastName");
    txtusername = new javax.swing.JTextField();
    txtusername.setName("textUserName");

    buttonCancel = new JButton();
    buttonCancel.setName("buttonCancel");
    txtpassword = new javax.swing.JPasswordField();
    txtpassword.setName("textPassword");
    status = new JLabel();
    status.setName("statustext");

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("User Creation"));

    labelUserId.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelUserId.setText("User ID");

    labelUserFirstName.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelUserFirstName.setText("FirstName");

    labelUserLastName.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelUserLastName.setText("LastName");

    labelUsername.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelUsername.setText("User Name");

    labelPassword.setFont(new java.awt.Font("Tahoma", 1, 12));
    labelPassword.setText("Password");

    txtuserid.setFont(new java.awt.Font("Tahoma", 1, 18));
    txtuserid.setForeground(new java.awt.Color(255, 0, 0));

    buttonAddUser.setText("Add");

    buttonAddUser.addActionListener(
            evt -> {
              firstName = txtfirstname.getText();
              lastName = txtlastname.getText();
              username = txtusername.getText();
              password = txtpassword.getText();

              user = new User(id, firstName, lastName, username, password);

              boolean success = userDAO.insertNewUser(user);

              if (success) {
                successPane.setMessage("User created!");
                JDialog dialog = successPane.createDialog(null, "Status");
                dialog.setVisible(true);
              } else {
                successPane.setMessage("Error creating user!");
                JDialog dialog = successPane.createDialog(null, "Status");
                dialog.setVisible(true);
              }
            });

    buttonCancel.setText("Cancel");
    buttonCancel.addActionListener(this::buttonCancelAction);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
            jPanel1Layout
                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(
                            jPanel1Layout
                                    .createSequentialGroup()
                                    .addGap(44, 44, 44)
                                    .addGroup(
                                            jPanel1Layout
                                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelUserId)
                                                    .addComponent(labelUserFirstName)
                                                    .addComponent(labelUserLastName)
                                                    .addComponent(labelUsername)
                                                    .addComponent(labelPassword))
                                    .addGap(55, 55, 55)
                                    .addGroup(
                                            jPanel1Layout
                                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtuserid)
                                                    .addComponent(txtfirstname)
                                                    .addComponent(txtlastname)
                                                    .addComponent(txtusername)
                                                    .addComponent(
                                                            txtpassword,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            140,
                                                            Short.MAX_VALUE))
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(
                            javax.swing.GroupLayout.Alignment.TRAILING,
                            jPanel1Layout
                                    .createSequentialGroup()
                                    .addContainerGap(208, Short.MAX_VALUE)
                                    .addComponent(
                                            status,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            109,
                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(
                                            buttonAddUser,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            109,
                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(
                                            buttonCancel,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            107,
                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)));
    jPanel1Layout.setVerticalGroup(
            jPanel1Layout
                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(
                            jPanel1Layout
                                    .createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addGroup(
                                            jPanel1Layout
                                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(
                                                            jPanel1Layout
                                                                    .createSequentialGroup()
                                                                    .addGroup(
                                                                            jPanel1Layout
                                                                                    .createParallelGroup(
                                                                                            javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                    .addGroup(
                                                                                            jPanel1Layout
                                                                                                    .createSequentialGroup()
                                                                                                    .addGroup(
                                                                                                            jPanel1Layout
                                                                                                                    .createParallelGroup(
                                                                                                                            javax.swing.GroupLayout.Alignment
                                                                                                                                    .BASELINE)
                                                                                                                    .addComponent(labelUserId)
                                                                                                                    .addComponent(txtuserid))
                                                                                                    .addGap(37, 37, 37)
                                                                                                    .addGroup(
                                                                                                            jPanel1Layout
                                                                                                                    .createParallelGroup(
                                                                                                                            javax.swing.GroupLayout.Alignment
                                                                                                                                    .BASELINE)
                                                                                                                    .addComponent(labelUserFirstName)
                                                                                                                    .addComponent(
                                                                                                                            txtfirstname,
                                                                                                                            javax.swing.GroupLayout
                                                                                                                                    .PREFERRED_SIZE,
                                                                                                                            javax.swing.GroupLayout
                                                                                                                                    .DEFAULT_SIZE,
                                                                                                                            javax.swing.GroupLayout
                                                                                                                                    .PREFERRED_SIZE))
                                                                                                    .addGap(44, 44, 44)
                                                                                                    .addComponent(labelUserLastName))
                                                                                    .addComponent(
                                                                                            txtlastname,
                                                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                            javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                    .addGap(49, 49, 49)
                                                                    .addComponent(labelUsername))
                                                    .addComponent(
                                                            txtusername,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(46, 46, 46)
                                    .addGroup(
                                            jPanel1Layout
                                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(labelPassword)
                                                    .addComponent(
                                                            txtpassword,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(
                                            javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                    .addGroup(
                                            jPanel1Layout
                                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(
                                                            status,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                            48,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(
                                                            buttonAddUser,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                            48,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(
                                                            buttonCancel,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                                            44,
                                                            javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap()));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout
                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(
                                            jPanel1,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(22, Short.MAX_VALUE)));
    layout.setVerticalGroup(
            layout
                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(
                            layout
                                    .createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addComponent(
                                            jPanel1,
                                            javax.swing.GroupLayout.PREFERRED_SIZE,
                                            javax.swing.GroupLayout.DEFAULT_SIZE,
                                            javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(14, Short.MAX_VALUE)));

    pack();
  }

  /**
   * Used to cancel the userCreation form.
   *
   */
  private void buttonCancelAction(
          java.awt.event.ActionEvent evt) {
    this.hide();
  }

}