package cc.mattg.cpitemadder;

import cc.mattg.cpitemadder.gui.ItemAdderView;

import javax.swing.*;
import java.awt.*;

public class ItemAdder {

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            try {
               UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException var4) {
               var4.printStackTrace();
            } catch (InstantiationException var5) {
               var5.printStackTrace();
            } catch (IllegalAccessException var6) {
               var6.printStackTrace();
            } catch (UnsupportedLookAndFeelException var7) {
               var7.printStackTrace();
            }

            ItemAdderModel model = new ItemAdderModel();
            ItemAdderView view = new ItemAdderView();
            ItemAdderController controller = new ItemAdderController(model, view);
            view.registerListener(controller);
            view.setDefaultCloseOperation(3);
            view.setSize(500, 325);
            view.setResizable(false);
            view.setTitle("Matt's Item Adder - Not Running");
            view.setVisible(true);
            view.setLocationRelativeTo((Component)null);
         }
      });
   }
}
