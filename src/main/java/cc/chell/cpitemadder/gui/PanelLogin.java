package cc.chell.cpitemadder.gui;

import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelLogin extends JPanel {

   private static final long serialVersionUID = 1L;
   private JTextField usernameField;
   private JPasswordField passwordField;
   private JLabel lblItemsFile;
   private JButton btnBrowse;
   private JButton btnAddItems;


   public PanelLogin() {
      this.setLayout((LayoutManager)null);
      JLabel lblUsername = new JLabel("Username:");
      lblUsername.setBounds(129, 61, 52, 14);
      this.add(lblUsername);
      this.usernameField = new JTextField();
      this.usernameField.setBounds(191, 58, 149, 20);
      this.add(this.usernameField);
      JLabel lblPassword = new JLabel("Password:");
      lblPassword.setBounds(131, 90, 50, 14);
      this.add(lblPassword);
      this.passwordField = new JPasswordField();
      this.passwordField.setBounds(191, 87, 149, 20);
      this.add(this.passwordField);
      JLabel lblItems = new JLabel("Items:");
      lblItems.setBounds(150, 119, 31, 14);
      this.add(lblItems);
      this.lblItemsFile = new JLabel("");
      this.lblItemsFile.setBounds(279, 119, 61, 14);
      this.add(this.lblItemsFile);
      this.btnBrowse = new JButton("Browse...");
      this.btnBrowse.setBounds(190, 115, 79, 23);
      this.add(this.btnBrowse);
      this.btnAddItems = new JButton("Add Items");
      this.btnAddItems.setBounds(190, 151, 89, 24);
      this.add(this.btnAddItems);
   }

   public JTextField getUsernameField() {
      return this.usernameField;
   }

   public JPasswordField getPasswordField() {
      return this.passwordField;
   }

   public JLabel getLblItemsFile() {
      return this.lblItemsFile;
   }

   public JButton getBtnBrowse() {
      return this.btnBrowse;
   }

   public JButton getBtnAddItems() {
      return this.btnAddItems;
   }
}
