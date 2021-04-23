package airline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Login class that displays the Login GUI to the user.
 */
public class Login extends javax.swing.JFrame {

  /** Creates new form Login */
  public Login() {
    initComponents();
  }

  Connection con;
  PreparedStatement pst;

  /**
   * Initializes the components that are part of the login form.
   */
  @SuppressWarnings("unchecked")
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    txtuser = new javax.swing.JTextField();
    txtuser.setName("textUser");
    txtpass = new javax.swing.JPasswordField();
    txtpass.setName("textPass");
    loginstatus = new javax.swing.JLabel();
    loginstatus.setName("loginStatus");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanel1.setBorder(
        javax.swing.BorderFactory.createTitledBorder(
            null,
            "Login",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new java.awt.Font("Tahoma", 1, 18))); // NOI18N

    jLabel1.setText("UserName");

    jLabel2.setText("Password");

    jButton1.setText("Login");
    jButton1.setName("buttonLogin");
    jButton1.addActionListener(
            this::jButton1ActionPerformed);

    jButton2.setText("Cancel");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                jPanel1Layout
                    .createSequentialGroup()
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                jPanel1Layout
                                    .createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addGroup(
                                        jPanel1Layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(loginstatus)
                                            .addComponent(jLabel2))
                                    .addGap(52, 52, 52)
                                    .addGroup(
                                        jPanel1Layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtuser)
                                            .addComponent(
                                                txtpass,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                177,
                                                Short.MAX_VALUE)))
                            .addGroup(
                                jPanel1Layout
                                    .createSequentialGroup()
                                    .addGap(136, 136, 136)
                                    .addComponent(
                                        jButton1,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        97,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(
                                        jButton2,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        116,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(25, Short.MAX_VALUE)));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                jPanel1Layout
                    .createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(loginstatus)
                            .addComponent(
                                txtuser,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(43, 43, 43)
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(
                                txtpass,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(
                                jButton1,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                39,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(
                                jButton2,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                36,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(45, 45, 45)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(
                        jPanel1,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(44, Short.MAX_VALUE)));
    layout.setVerticalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(
                        jPanel1,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE)));

    pack();
    setLocationRelativeTo(null);
  }

  /**
   * Called when the login button is pressed. Checks database for correct username
   * and password and if a match, logs the user in.
   * @param evt
   */
  private void jButton1ActionPerformed(
      java.awt.event.ActionEvent evt) { // GEN-FIRST:event_jButton1ActionPerformed
    // TODO add your handling code here:

    String username = txtuser.getText();
    String password = txtpass.getText();

    if (username.isEmpty() || password.isEmpty()) {
      JOptionPane.showMessageDialog(this, "UserName or Password Blank");
    } else {
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "password");
        pst = con.prepareStatement("select * from user where username = ? and password = ?");
        pst.setString(1, username);
        pst.setString(2, password);

        ResultSet rs;
        rs = pst.executeQuery();

        if (rs.next()) {
          Main m = new Main();
          this.hide();
          m.setVisible(true);
          loginstatus.setText("true");

        } else {
          loginstatus.setText("false");
          JOptionPane dg = new JOptionPane();
          dg.setName("dialog");
          dg.showMessageDialog(this, "UserName or Password do not Match");
          txtuser.setText("");
          txtpass.setText("");
          txtuser.requestFocus();
        }

      } catch (ClassNotFoundException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SQLException ex) {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  /** @param args the command line arguments */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info :
          javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
    }
    // </editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(
            () -> new Login().setVisible(true));
  }

  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel loginstatus;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPasswordField txtpass;
  private javax.swing.JTextField txtuser;

}
