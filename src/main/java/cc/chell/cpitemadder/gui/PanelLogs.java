package cc.chell.cpitemadder.gui;

import javax.swing.*;
import java.awt.*;

public class PanelLogs extends JPanel {

   private static final long serialVersionUID = 1L;
   private JTextArea textArea;
   private JScrollPane scrollPane;


   public PanelLogs() {
      this.setLayout((LayoutManager)null);
      this.textArea = new JTextArea();
      this.textArea.setEditable(false);
      this.textArea.setFont(new Font("Monospaced", 0, 11));
      this.textArea.setLineWrap(true);
      this.scrollPane = new JScrollPane(this.textArea);
      this.scrollPane.setBounds(11, 11, 447, 210);
      this.add(this.scrollPane);
   }

   public JTextArea getTextArea() {
      return this.textArea;
   }

   public JScrollPane getScrollPane() {
      return this.scrollPane;
   }
}
