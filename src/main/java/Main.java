/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends javax.swing.JFrame {

  private javax.swing.JDesktopPane pane;
  private javax.swing.JMenu menuCustomerMenu;
  private javax.swing.JMenu menuTicketsMenu;
  private javax.swing.JMenu menuFlightMenu;
  private javax.swing.JMenu menuUserMenu;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenuItem menuItemAddCustomer;
  private javax.swing.JMenuItem menuItemSearchCustomer;
  private javax.swing.JMenuItem menuItemBookTicket;
  private javax.swing.JMenuItem menuItemAddFlight;
  private javax.swing.JMenuItem menuItemUserCreation;
  private javax.swing.JMenuItem menuItemTicketReport;

  /** Creates new form Main */
  public Main() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  private void initComponents() {

    pane = new javax.swing.JDesktopPane();
    pane.setName("desktopPane");
    menuBar = new javax.swing.JMenuBar();
    menuBar.setName("menuBar");
    menuCustomerMenu = new javax.swing.JMenu();
    menuCustomerMenu.setName("customerMenu");
    menuItemAddCustomer = new javax.swing.JMenuItem();
    menuItemAddCustomer.setName("addCustomerMenuItem");
    menuItemSearchCustomer = new javax.swing.JMenuItem();
    menuItemSearchCustomer.setName("searchCustomerMenuItem");
    menuTicketsMenu = new javax.swing.JMenu();
    menuTicketsMenu.setName("ticketsMenu");
    menuItemBookTicket = new javax.swing.JMenuItem();
    menuItemBookTicket.setName("bookTicketMenuItem");
    menuItemTicketReport = new javax.swing.JMenuItem();
    menuItemTicketReport.setName("ticketReportMenuItem");
    menuFlightMenu = new javax.swing.JMenu();
    menuFlightMenu.setName("flightMenu");
    menuItemAddFlight = new javax.swing.JMenuItem();
    menuItemAddFlight.setName("addFlightMenuItem");
    menuUserMenu = new javax.swing.JMenu();
    menuUserMenu.setName("userMenu");
    menuItemUserCreation = new javax.swing.JMenuItem();
    menuItemUserCreation.setName("userCreationMenuItem");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setPreferredSize(new java.awt.Dimension(1366, 768));

    javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
    pane.setLayout(paneLayout);
    paneLayout.setHorizontalGroup(
        paneLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE));
    paneLayout.setVerticalGroup(
        paneLayout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE));

    menuCustomerMenu.setText("Customer");

    menuItemAddCustomer.setText("Add Customer");
    menuItemAddCustomer.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemAddCustomerActionPerformed();
          }
        });
    menuCustomerMenu.add(menuItemAddCustomer);

    menuItemSearchCustomer.setText("Search Customer");
    menuItemSearchCustomer.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemSearchCustomerActionPerformed();
          }
        });
    menuCustomerMenu.add(menuItemSearchCustomer);

    menuBar.add(menuCustomerMenu);

    menuTicketsMenu.setText("Tickets");

    menuItemBookTicket.setText("Book Ticket");
    menuItemBookTicket.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemBookTicketActionPerformed();
          }
        });
    menuTicketsMenu.add(menuItemBookTicket);

    menuItemTicketReport.setText("Ticket Report");
    menuItemTicketReport.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemTicketReportActionPerformed();
          }
        });
    menuTicketsMenu.add(menuItemTicketReport);

    menuBar.add(menuTicketsMenu);

    menuFlightMenu.setText("Flight");

    menuItemAddFlight.setText("Add Flight");
    menuItemAddFlight.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemAddFlightActionPerformed();
          }
        });
    menuFlightMenu.add(menuItemAddFlight);

    menuBar.add(menuFlightMenu);

    menuUserMenu.setText("User");

    menuItemUserCreation.setText("UserCreation");
    menuItemUserCreation.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            menuItemUserCreationActionPerformed();
          }
        });
    menuUserMenu.add(menuItemUserCreation);

    menuBar.add(menuUserMenu);

    setJMenuBar(menuBar);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pane));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(pane));

    pack();
  }

  /**
   * Menu item to navigate to the addCustomer window.
   * @return True if successful.
   */
  public boolean menuItemAddCustomerActionPerformed() {
    addCustomer cus = new addCustomer();
    pane.add(cus);
    cus.setVisible(true);

    return cus.isVisible();
  }

  /**
   * Menu item to navigate to the searchCustomer window.
   * @return True if successful.
   */
  public boolean menuItemSearchCustomerActionPerformed() {
    searchCustomer scus = new searchCustomer();
    pane.add(scus);
    scus.setVisible(true);

    return scus.isVisible();
  }

  /**
   * Menu item to navigate to the addFlight window.
   * @return True if successful.
   */
  public boolean menuItemAddFlightActionPerformed() {
    addflight f = new addflight();
    pane.add(f);
    f.setVisible(true);

    return f.isVisible();
  }

  /**
   * Menu item to navigate to the ticket window.
   * @return True if successful.
   */
  public boolean menuItemBookTicketActionPerformed() {
    ticket t = new ticket();
    pane.add(t);
    t.setVisible(true);

    return t.isVisible();
  }

  /**
   * Menu item to navigate to the ticket report window.
   * @return True if successful.
   */
  public boolean menuItemTicketReportActionPerformed() {
    ticketreport ti = new ticketreport();
    pane.add(ti);
    ti.setVisible(true);

    return ti.isVisible();
  }

  /**
   * Menu item to navigate to the userCreation window.
   * @return True if successful.
   */
  public boolean menuItemUserCreationActionPerformed() {
    userCreation u = new userCreation();
    pane.add(u);
    u.setVisible(true);

    return u.isVisible();
  }

  /*
  All of the following methods are getters added to assist in testing of the Main window as
  well as all of the menu links.
   */
  public JDesktopPane getPane() {
    return pane;
  }

  public JMenu getMenuCustomerMenu() {
    return menuCustomerMenu;
  }

  public JMenu getMenuTicketsMenu() {
    return menuTicketsMenu;
  }

  public JMenu getMenuFlightMenu() {
    return menuFlightMenu;
  }

  public JMenu getMenuUserMenu() {
    return menuUserMenu;
  }

  public JMenuBar _getMenuBar() {
    return menuBar;
  }

  public JMenuItem getMenuItemAddCustomer() {
    return menuItemAddCustomer;
  }

  public JMenuItem getMenuItemSearchCustomer() {
    return menuItemSearchCustomer;
  }

  public JMenuItem getMenuItemBookTicket() {
    return menuItemBookTicket;
  }

  public JMenuItem getMenuItemAddFlight() {
    return menuItemAddFlight;
  }

  public JMenuItem getMenuItemUserCreation() {
    return menuItemUserCreation;
  }

  public JMenuItem getMenuItemTicketReport() {
    return menuItemTicketReport;
  }
}
