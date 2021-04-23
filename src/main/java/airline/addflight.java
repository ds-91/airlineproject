package airline;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * addflight user interface class. Handles when a user wants to create
 * a new flight.
 */
public class addflight extends javax.swing.JInternalFrame {
  private javax.swing.JButton buttonAddFlight;
  private javax.swing.JButton buttonCancel;
  private javax.swing.JLabel labelFlightId;
  private javax.swing.JLabel labelFlightName;
  private javax.swing.JLabel labelFlightSource;
  private javax.swing.JLabel labelFlightDeparture;
  private javax.swing.JLabel labelFlightDate;
  private javax.swing.JLabel labelFlightDepartureTime;
  private javax.swing.JLabel labelFlightArrivalTime;
  private javax.swing.JLabel labelFlightCharge;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JTextField txtarrtime;
  private com.toedter.calendar.JDateChooser txtdate;
  private javax.swing.JComboBox<String> txtdepart;
  private javax.swing.JTextField txtdtime;
  private javax.swing.JTextField txtflightcharge;
  private javax.swing.JLabel txtflightid;
  private javax.swing.JTextField txtflightname;
  private javax.swing.JComboBox<String> txtsource;
  private JOptionPane successPane;

  /** Creates new form addflight */
  public addflight() {
    initComponents();
    autoID();
  }

  Connection con;
  PreparedStatement pst;
  FlightDAO flightDAO;

  /**
   * Initializes all the components that are in the addflight form.
   */
  private void initComponents() {
    flightDAO = new FlightDAO();

    jPanel1 = new javax.swing.JPanel();
    labelFlightId = new javax.swing.JLabel();
    txtflightid = new javax.swing.JLabel();
    txtflightid.setName("txtflightid");
    labelFlightName = new javax.swing.JLabel();
    labelFlightSource = new javax.swing.JLabel();
    labelFlightDeparture = new javax.swing.JLabel();
    txtflightname = new javax.swing.JTextField();
    txtflightname.setName("txtflightname");
    labelFlightDate = new javax.swing.JLabel();
    txtdate = new com.toedter.calendar.JDateChooser();
    txtdate.setName("txtdate");
    labelFlightDepartureTime = new javax.swing.JLabel();
    txtdtime = new javax.swing.JTextField();
    txtdtime.setName("txtdtime");
    txtarrtime = new javax.swing.JTextField();
    txtarrtime.setName("txtarrtime");
    labelFlightArrivalTime = new javax.swing.JLabel();
    labelFlightCharge = new javax.swing.JLabel();
    txtflightcharge = new javax.swing.JTextField();
    txtflightcharge.setName("txtflightcharge");
    buttonAddFlight = new javax.swing.JButton();
    buttonAddFlight.setName("buttonAddFlight");
    buttonCancel = new javax.swing.JButton();
    buttonCancel.setName("buttonCancel");
    txtsource = new javax.swing.JComboBox<>();
    txtsource.setName("txtsource");
    txtdepart = new javax.swing.JComboBox<>();
    txtdepart.setName("txtdepart");
    successPane = new JOptionPane();

    jPanel1.setBackground(new java.awt.Color(51, 51, 255));

    labelFlightId.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    labelFlightId.setForeground(new java.awt.Color(255, 255, 255));
    labelFlightId.setText("Flight ID");

    txtflightid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    txtflightid.setForeground(new java.awt.Color(255, 255, 0));
    txtflightid.setText("jLabel2");

    labelFlightName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    labelFlightName.setForeground(new java.awt.Color(255, 255, 255));
    labelFlightName.setText("Flight Name");

    labelFlightSource.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    labelFlightSource.setForeground(new java.awt.Color(255, 255, 255));
    labelFlightSource.setText("Source");

    labelFlightDeparture.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    labelFlightDeparture.setForeground(new java.awt.Color(255, 255, 255));
    labelFlightDeparture.setText("Departure");

    labelFlightDate.setForeground(new java.awt.Color(255, 255, 255));
    labelFlightDate.setText("Date");

    labelFlightDepartureTime.setForeground(new java.awt.Color(255, 255, 255));
    labelFlightDepartureTime.setText("Dep Time");

    labelFlightArrivalTime.setForeground(new java.awt.Color(255, 255, 255));
    labelFlightArrivalTime.setText("Arr Time");

    labelFlightCharge.setForeground(new java.awt.Color(255, 255, 255));
    labelFlightCharge.setText("Flight Charge");

    buttonAddFlight.setText("Add");
    /**
     * Runs when the 'Add' button is clicked. Gathers user input from all
     * compnents on the form and creates a new flight object with them.
     * Finally, creates a new flight in the database.
     */
    buttonAddFlight.addActionListener(
        evt -> {
          String id = txtflightid.getText();
          String flightname = txtflightname.getText();
          String source = txtsource.getSelectedItem().toString().trim();
          String depart = txtdepart.getSelectedItem().toString().trim();
          DateFormat da = new SimpleDateFormat("yyyy-MM-dd");
          String date = da.format(txtdate.getDate());
          String departtime = txtdtime.getText();
          String arrtime = txtarrtime.getText();
          String flightcharge = txtflightcharge.getText();

          Flight flight =
              new Flight(id, flightname, source, depart, date, departtime, arrtime, flightcharge);

          boolean success = flightDAO.createFlight(flight);

          if (success) {
            successPane.setMessage("Flight created!");
            JDialog diag = successPane.createDialog(null, "Status");
            diag.setVisible(true);
          } else {
            successPane.setMessage("Error creating flight!");
            JDialog diag = successPane.createDialog(null, "Status");
            diag.setVisible(true);
          }
        });

    buttonCancel.setText("Cancel");
    /**
     * Runs when the 'Cancel' button is pressed. Closes the current form.
     */
    buttonCancel.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCancelActionPerformed(evt);
          }
        });

    txtsource.setModel(
        new javax.swing.DefaultComboBoxModel<>(
            new String[] {"India\t", "Srilanka", "Uk", "Usa", "Canada", "Chinna"}));

    txtdepart.setModel(
        new javax.swing.DefaultComboBoxModel<>(
            new String[] {"India\t", "Srilanka", "Uk", "Usa", "Canada", "Chinna"}));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                jPanel1Layout
                    .createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(
                                jPanel1Layout
                                    .createSequentialGroup()
                                    .addGroup(
                                        jPanel1Layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelFlightId)
                                            .addComponent(labelFlightName))
                                    .addGroup(
                                        jPanel1Layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(
                                                jPanel1Layout
                                                    .createSequentialGroup()
                                                    .addGap(38, 38, 38)
                                                    .addComponent(txtflightid))
                                            .addGroup(
                                                jPanel1Layout
                                                    .createSequentialGroup()
                                                    .addGap(28, 28, 28)
                                                    .addComponent(
                                                        txtflightname,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        136,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(
                                javax.swing.GroupLayout.Alignment.TRAILING,
                                jPanel1Layout
                                    .createSequentialGroup()
                                    .addGroup(
                                        jPanel1Layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelFlightSource)
                                            .addComponent(labelFlightDeparture))
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                    .addGroup(
                                        jPanel1Layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtsource, 0, 136, Short.MAX_VALUE)
                                            .addComponent(
                                                txtdepart,
                                                0,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE))))
                    .addGap(103, 103, 103)
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                jPanel1Layout
                                    .createSequentialGroup()
                                    .addGroup(
                                        jPanel1Layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelFlightDate)
                                            .addComponent(labelFlightDepartureTime)
                                            .addComponent(labelFlightArrivalTime))
                                    .addGap(62, 62, 62)
                                    .addGroup(
                                        jPanel1Layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(
                                                txtdate,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                174,
                                                Short.MAX_VALUE)
                                            .addComponent(txtdtime)
                                            .addComponent(txtarrtime)))
                            .addGroup(
                                jPanel1Layout
                                    .createSequentialGroup()
                                    .addComponent(labelFlightCharge)
                                    .addGap(52, 52, 52)
                                    .addComponent(
                                        txtflightcharge,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        172,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                jPanel1Layout
                    .createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(
                        buttonAddFlight,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        117,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(29, 29, 29)
                    .addComponent(
                        buttonCancel,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        110,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(39, 39, 39)));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                jPanel1Layout
                    .createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(
                                jPanel1Layout
                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelFlightId)
                                    .addComponent(txtflightid)
                                    .addComponent(labelFlightDate))
                            .addComponent(
                                txtdate,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFlightName)
                            .addComponent(
                                txtflightname,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelFlightDepartureTime)
                            .addComponent(
                                txtdtime,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28)
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelFlightSource)
                            .addComponent(
                                txtarrtime,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelFlightArrivalTime)
                            .addComponent(
                                txtsource,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                jPanel1Layout
                                    .createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(
                                        jPanel1Layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(labelFlightDeparture)
                                            .addComponent(
                                                txtdepart,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(
                                jPanel1Layout
                                    .createSequentialGroup()
                                    .addGap(34, 34, 34)
                                    .addGroup(
                                        jPanel1Layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(labelFlightCharge)
                                            .addComponent(
                                                txtflightcharge,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                    .addGroup(
                        jPanel1Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(
                                buttonAddFlight,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                46,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(
                                buttonCancel,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                46,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(43, 43, 43)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addGap(34, 34, 34)
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
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout
                    .createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(
                        jPanel1,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(39, 39, 39)));

    pack();
  } // </editor-fold>//GEN-END:initComponents

  /**
   * Generates the ID of the next flight to be inserted into the database.
   * Retrieves the highest ID from the database and then increments it by one.
   */
  public void autoID() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mysql://localhost/airline", "root", "password");
      Statement s = con.createStatement();
      ResultSet rs = s.executeQuery("select MAX(id) from flight");
      rs.next();
      rs.getString("MAX(id)");
      if (rs.getString("MAX(id)") == null) {
        txtflightid.setText("FO001");
      } else {
        long id =
            Long.parseLong(rs.getString("MAX(id)").substring(2, rs.getString("MAX(id)").length()));
        id++;
        txtflightid.setText("FO" + String.format("%03d", id));
      }

    } catch (ClassNotFoundException ex) {
      Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
      Logger.getLogger(addCustomer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public JButton getButtonAddFlight() {
    return buttonAddFlight;
  }

  public JButton getButtonCancel() {
    return buttonCancel;
  }

  public JLabel getLabelFlightId() {
    return labelFlightId;
  }

  public JTextField getTxtarrtime() {
    return txtarrtime;
  }

  public JDateChooser getTxtdate() {
    return txtdate;
  }

  public JComboBox<String> getTxtdepart() {
    return txtdepart;
  }

  public JTextField getTxtdtime() {
    return txtdtime;
  }

  public JTextField getTxtflightcharge() {
    return txtflightcharge;
  }

  public JLabel getTxtflightid() {
    return txtflightid;
  }

  public JTextField getTxtflightname() {
    return txtflightname;
  }

  public JComboBox<String> getTxtsource() {
    return txtsource;
  }

  public JOptionPane getSuccessPane() {
    return successPane;
  }

  /**
   * Ran when the cancel button is pressed. Closes the current window.
   * @param evt
   */
  private void buttonCancelActionPerformed(
      java.awt.event.ActionEvent evt) {
    this.hide();
  }
}
