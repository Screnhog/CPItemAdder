package cc.chell.cpitemadder;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.utils.Base64Coder;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.util.Map;

public class WebServices {

   public static String BASE_URL = "https://api.disney.com/clubpenguin/mobile/v2";
   public static String API_SIG = "FD 9B407F96-418B-4E0B-93DB-3AD33CC7D72E:205EF7823B24EE5277E318E061E5557F4648F1BF4CCFB457";


   public static HttpResponse authToken(String username, String password) throws UnirestException {
      return Unirest.get(BASE_URL + "/authToken?appId=CPMCAPP&appVersion=1.4&language=en").header("Authorization", "Basic " + Base64Coder.encodeString(username + ":" + password) + "," + API_SIG).asJson();
   }

   public static HttpResponse purchase(String authToken, String itemId) throws UnirestException {
       HttpRequestWithBody request = Unirest.post(BASE_URL + "/purchase?catalogId=500435792&itemType=paper_item&itemId=" + itemId).header("Authorization", "Basic " + Base64Coder.encodeString(authToken + ":") + "," + API_SIG);
       System.out.print("HEADERS: ");
       for (Object e : request.getHeaders().entrySet()) {
           Map.Entry entry = (Map.Entry)e;
           String key = (String)entry.getKey();
           String val = (String)entry.getValue();
           System.out.println(key+"|"+val);
       }
       return request.asJson();
   }
}
