package probablyNotUsable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static java.util.Objects.hash;
import javax.xml.bind.DatatypeConverter;


public class Stock {
    private long id;
    private String name;
    private String commnet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommnet() {
        return commnet;
    }

    public void setCommnet(String commnet) {
        this.commnet = commnet;
    }
    
    
          public void heslo(String heslo) {
              /*pripravíme si reťazec na hasovanie*/
              try {
                  String stringForHash = heslo;
                  /*vezmeme si hashovací algoritmus SHA-256*/
                  MessageDigest md = MessageDigest.getInstance("SHA-256");
                  md.update(stringForHash.getBytes());
                  byte[] data = md.digest();
                  String hashString = DatatypeConverter.printHexBinary(data);
                  System.out.println(hashString);
              } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
              }
         
          } 
          
          public static void main(String[] args) {
        Stock s = new Stock();
        s.heslo("heslo");
    }
}
