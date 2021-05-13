package domain;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


import javax.xml.bind.annotation.adapters.XmlAdapter;

public class IntegerAdapter extends XmlAdapter<String, Integer> implements Serializable{
    
    public Integer unmarshal(String s) {
        return Integer.parseInt(s);
    }
 
    public String marshal(Integer number) {
        if (number == null) return "";
         
        return number.toString();
    }
    public String createRandomCode(String id){   
        char[] chars = id.toCharArray();
      int randomNum = ThreadLocalRandom.current().nextInt(0, 43242341 + 1);
           StringBuilder sb = new StringBuilder();
           Random random = new SecureRandom();
           for (int i = 0; i < randomNum; i++) {
               char c = chars[random.nextInt(chars.length)];
               sb.append(c);
           }
           String output = sb.toString();
           System.out.println(output);
           return output ;
       } 
}