package cc.chell.cpitemadder.gui;

import cc.chell.cpitemadder.ItemAdderController;

import java.awt.Component;
import javax.swing.AbstractButton;
import javax.swing.JFrame;

public class ItemAdderView extends JFrame {

   private static final long serialVersionUID = 1L;
   private PanelMain mainPanel = new PanelMain();


   public ItemAdderView() {
      this.add(this.mainPanel);
   }

   public void registerListener(ItemAdderController controller) {
      Component[] components = this.mainPanel.getLoginPanel().getComponents();
      Component[] var6 = components;
      int var5 = components.length;

      for(int var4 = 0; var4 < var5; ++var4) {
         Component component = var6[var4];
         if(component instanceof AbstractButton) {
            AbstractButton button = (AbstractButton)component;
            button.addActionListener(controller);
         }
      }

   }

   public PanelMain getMainPanel() {
      return this.mainPanel;
   }
}
