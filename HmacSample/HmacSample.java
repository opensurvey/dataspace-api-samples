package HmacSample;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


public class HmacSample {


    private static final String SECRET_KEY = "SECRET_FROM_DATASPACE";


    private static final String MAC_ALGORITHM = "HmacSHA256";
    private static final String SERIAL_STORE_UID_TEMPLATE = "%s?store=%s&uid=%s"; // key 기준 사전순으로 배열 및 key 를 소문자로 변환


    private static String sign(String message) {
        try {
            Mac mac = Mac.getInstance(MAC_ALGORITHM);
            mac.init(new SecretKeySpec(SECRET_KEY.getBytes(), MAC_ALGORITHM));
            return Base64.getUrlEncoder().encodeToString(mac.doFinal(message.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static String signForLink(String serial, String uid, String store) {
        return sign(String.format(SERIAL_STORE_UID_TEMPLATE, serial, store, uid)).substring(0, 8);
    }


    public static void main(String[] args) {
	  // Answer Link Sample hmac XUVJFZA_
        System.out.printf("Answer Link Sample hmac %s\n", signForLink("aLBNYVAk1Ku", "TEST_UID", "gangnam-store"));
    }


}

