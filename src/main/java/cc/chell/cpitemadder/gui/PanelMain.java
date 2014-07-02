package cc.chell.cpitemadder.gui;

import javax.swing.*;
import java.awt.*;

public class PanelMain extends JPanel {

   private static final long serialVersionUID = 1L;
   private PanelLogin loginPanel;
   private PanelLogs logsPanel;
   private JLabel lblAdded;
   private JLabel lblFailed;
   private JLabel lblLeft;
   private JProgressBar progressBar;


   public PanelMain() {
      this.setLayout((LayoutManager)null);
      JTabbedPane tabbedPane = new JTabbedPane(1);
      tabbedPane.setBounds(10, 11, 474, 260);
      this.add(tabbedPane);
      this.loginPanel = new PanelLogin();
      tabbedPane.addTab("Login", (Icon)null, this.loginPanel, (String)null);
      this.logsPanel = new PanelLogs();
      tabbedPane.addTab("Logs", (Icon)null, this.logsPanel, (String)null);
      JSeparator separator = new JSeparator();
      separator.setBounds(0, 274, 494, 2);
      this.add(separator);
      this.lblAdded = new JLabel("Added: 0");
      this.lblAdded.setHorizontalAlignment(0);
      this.lblAdded.setForeground(new Color(100, 128, 51));
      this.lblAdded.setFont(new Font("Tahoma", 0, 11));
      this.lblAdded.setBounds(9, 279, 110, 14);
      this.add(this.lblAdded);
      JLabel lblSeparator1 = new JLabel("|");
      lblSeparator1.setFont(new Font("Tahoma", 0, 14));
      lblSeparator1.setBounds(126, 278, 5, 14);
      this.add(lblSeparator1);
      this.lblFailed = new JLabel("Failed: 0");
      this.lblFailed.setHorizontalAlignment(0);
      this.lblFailed.setForeground(Color.RED);
      this.lblFailed.setFont(new Font("Tahoma", 0, 11));
      this.lblFailed.setBounds(138, 279, 110, 14);
      this.add(this.lblFailed);
      JLabel lblSeparator2 = new JLabel("|");
      lblSeparator2.setFont(new Font("Tahoma", 0, 14));
      lblSeparator2.setBounds(255, 278, 5, 14);
      this.add(lblSeparator2);
      this.lblLeft = new JLabel("Left: 0");
      this.lblLeft.setHorizontalAlignment(0);
      this.lblLeft.setFont(new Font("Tahoma", 0, 11));
      this.lblLeft.setBounds(267, 279, 110, 14);
      this.add(this.lblLeft);
      this.progressBar = new JProgressBar();
      this.progressBar.setBounds(386, 278, 98, 17);
      this.add(this.progressBar);
   }

   public PanelLogin getLoginPanel() {
      return this.loginPanel;
   }

   public PanelLogs getLogsPanel() {
      return this.logsPanel;
   }

   public JLabel getLblAdded() {
      return this.lblAdded;
   }

   public void setLblAdded(JLabel lblAdded) {
      this.lblAdded = lblAdded;
   }

   public JLabel getLblFailed() {
      return this.lblFailed;
   }

   public void setLblFailed(JLabel lblFailed) {
      this.lblFailed = lblFailed;
   }

   public JLabel getLblLeft() {
      return this.lblLeft;
   }

   public void setLblLeft(JLabel lblLeft) {
      this.lblLeft = lblLeft;
   }

   public JProgressBar getProgressBar() {
      return this.progressBar;
   }
}
