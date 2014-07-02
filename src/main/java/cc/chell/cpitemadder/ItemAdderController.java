package cc.chell.cpitemadder;

import cc.chell.cpitemadder.gui.ItemAdderView;
import cc.chell.cpitemadder.gui.PanelLogin;
import cc.chell.cpitemadder.gui.PanelLogs;
import cc.chell.cpitemadder.gui.PanelMain;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class ItemAdderController implements ActionListener {

   private ItemAdderModel model;
   private ItemAdderView view;
   private PanelMain mainPanel;
   private PanelLogin loginPanel;
   private PanelLogs logsPanel;


   public ItemAdderController(ItemAdderModel model, ItemAdderView view) {
      this.model = model;
      this.view = view;
      this.mainPanel = view.getMainPanel();
      this.loginPanel = this.mainPanel.getLoginPanel();
      this.logsPanel = this.mainPanel.getLogsPanel();
   }

   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();
      if(command.equals("Browse...")) {
         this.handleBtnBrowse();
      } else if(command.equals("Add Items")) {
         this.handleBtnAddItems();
      }

   }

   private void handleBtnBrowse() {
      JFileChooser fileChooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", new String[]{"txt", "text"});
      fileChooser.setFileFilter(filter);
      int returnVal = fileChooser.showOpenDialog((Component)null);
      if(returnVal == 0) {
         String filePath = fileChooser.getSelectedFile().getAbsolutePath();
         int amountItems = this.model.loadItemsFile(filePath);
         if(amountItems > 0) {
            String fileName = fileChooser.getSelectedFile().getName();
            this.loginPanel.getLblItemsFile().setText(fileName);
            this.mainPanel.getProgressBar().setMaximum(amountItems);
         }
      }

   }

   private void handleBtnAddItems() {
      final String username = this.loginPanel.getUsernameField().getText();
      final String password = new String(this.loginPanel.getPasswordField().getPassword());
      final List items = this.model.getItems();
      if(!username.equals("") && !password.equals("") && !items.isEmpty()) {
         this.model.setSuccessCount(0);
         this.model.setFailureCount(0);
         this.mainPanel.getLblAdded().setText("Added: 0");
         this.mainPanel.getLblFailed().setText("Failed: 0");
         this.mainPanel.getLblLeft().setText("Left: 0");
         this.mainPanel.getProgressBar().setValue(0);
         this.logsPanel.getTextArea().setText("");
         this.toggleLoginInterface();
         (new Thread(new Runnable() {
            public void run() {
               boolean successfulLogin = ItemAdderController.this.model.login(username, password);
               if(successfulLogin) {
                  ItemAdderController.this.view.setTitle("Sentrix\'s Item Adder - Running");

                  String message;
                  for(Iterator var3 = items.iterator(); var3.hasNext(); ItemAdderController.this.logMessage(message)) {
                     String item = (String)var3.next();
                     boolean successfulAdd = ItemAdderController.this.model.addItem(item);
                     if(successfulAdd) {
                        ItemAdderController.this.mainPanel.getLblAdded().setText("Added: " + ItemAdderController.this.model.getSuccessCount());
                     } else {
                        ItemAdderController.this.mainPanel.getLblFailed().setText("Failed: " + ItemAdderController.this.model.getFailureCount());
                     }

                     ItemAdderController.this.mainPanel.getLblLeft().setText("Left: " + (ItemAdderController.this.model.getItems().size() - (ItemAdderController.this.model.getSuccessCount() + ItemAdderController.this.model.getFailureCount())));
                     ItemAdderController.this.mainPanel.getProgressBar().setValue(ItemAdderController.this.model.getSuccessCount() + ItemAdderController.this.model.getFailureCount());
                     message = "[ITEM " + item + "] " + ItemAdderController.this.model.getLastLogMessage();
                     if(!ItemAdderController.this.logsPanel.getTextArea().getText().equals("")) {
                        message = "\n\n" + message;
                     }
                  }

                  ItemAdderController.this.view.setTitle("Sentrix\'s Item Adder - Not Running");
                  ItemAdderController.this.toggleLoginInterface();
               } else {
                  JOptionPane.showMessageDialog((Component)null, "Login failed. Try again?");
                  ItemAdderController.this.toggleLoginInterface();
               }

            }
         })).start();
      } else {
         JOptionPane.showMessageDialog((Component)null, "Please complete all fields.");
      }

   }

   private void toggleLoginInterface() {
      this.loginPanel.getUsernameField().setEnabled(!this.loginPanel.getUsernameField().isEnabled());
      this.loginPanel.getPasswordField().setEnabled(!this.loginPanel.getPasswordField().isEnabled());
      this.loginPanel.getBtnBrowse().setEnabled(!this.loginPanel.getBtnBrowse().isEnabled());
      this.loginPanel.getBtnAddItems().setEnabled(!this.loginPanel.getBtnAddItems().isEnabled());
   }

   private void logMessage(String message) {
      final JScrollBar scrollBar = this.logsPanel.getScrollPane().getVerticalScrollBar();
      boolean end = scrollBar.getMaximum() == scrollBar.getValue() + scrollBar.getVisibleAmount();
      this.logsPanel.getTextArea().append(message);
      if(end) {
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               SwingUtilities.invokeLater(new Runnable() {
                  public void run() {
                     scrollBar.setValue(scrollBar.getMaximum());
                  }
               });
            }
         });
      }

   }
}
