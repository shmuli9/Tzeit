import java.security.MessageDigest;
import java.util.Scanner;
import java.util.UUID;

public class Utils {
    public static String toHex(String str) {
        StringBuilder sb = new StringBuilder();
        char[] ch = str.toCharArray();
        for (char c : ch) {
            sb.append(Integer.toHexString(c));
        }
        return sb.toString();
    }

    public static String hash(){
        String digest;
        try {
            MessageDigest salt = MessageDigest.getInstance("SHA-256");
            salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
            digest = toHex(salt.digest().toString());
        } catch (Exception e) {
            digest = "";
        }
        return digest;
    }
}
