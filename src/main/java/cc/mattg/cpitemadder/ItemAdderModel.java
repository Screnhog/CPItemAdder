package cc.mattg.cpitemadder;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemAdderModel {

   private List items = new ArrayList();
   private String lastLogMessage = "";
   private String authToken;
   private int successCount;
   private int failureCount;


   public int loadItemsFile(String filePath) {
      if(!this.items.isEmpty()) {
         this.items.clear();
      }

      BufferedReader reader = null;

      try {
         reader = new BufferedReader(new FileReader(filePath));
      } catch (FileNotFoundException var5) {
         var5.printStackTrace();
      }

      if(reader == null) {
         return 0;
      } else {
         String item = null;

         try {
            while((item = reader.readLine()) != null) {
               this.items.add(item);
            }
         } catch (IOException var6) {
            var6.printStackTrace();
         }

         return this.items.size();
      }
   }

   public boolean login(String username, String password) {
      HttpResponse res = null;

      try {
         res = WebServices.authToken(username, password);
      } catch (UnirestException var5) {
         var5.printStackTrace();
      }

      if(res != null && ((JsonNode)res.getBody()).getObject().has("authToken")) {
         this.authToken = ((JsonNode)res.getBody()).getObject().getString("authToken");
          System.out.print("AUTH TOKEN: ");
          System.out.println(this.authToken);
         return true;
      } else {
         return false;
      }
   }

   public boolean addItem(String itemId) {
      HttpResponse res = null;

      try {
         res = WebServices.purchase(this.authToken, itemId);
      } catch (UnirestException var4) {
         var4.printStackTrace();
      }

      if(res != null) {
         this.lastLogMessage = ((JsonNode)res.getBody()).toString();
         if(((JsonNode)res.getBody()).getObject().has("purchaseDate")) {
            ++this.successCount;
            return true;
         }
      }

      ++this.failureCount;
      return false;
   }

   public List getItems() {
      return this.items;
   }

   public String getLastLogMessage() {
      return this.lastLogMessage;
   }

   public int getSuccessCount() {
      return this.successCount;
   }

   public void setSuccessCount(int successCount) {
      this.successCount = successCount;
   }

   public int getFailureCount() {
      return this.failureCount;
   }

   public void setFailureCount(int failureCount) {
      this.failureCount = failureCount;
   }
}
