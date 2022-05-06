import java.math.BigDecimal;
import java.math.BigInteger;

public class UrlShortner {

    static String idToOrigUrl(BigInteger id) {
        char list[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuffer str = new StringBuffer();
        while(id.compareTo(BigInteger.ZERO) > 0) {
            str.append(list[id.mod(BigInteger.valueOf(62)).intValueExact()]);
            id = id.divide(BigInteger.valueOf(62));
        }
        return str.reverse().toString();
    }

    static String sanitizeUrl(String url) {
        if(url.substring(0,7).equals("http://")) {
            url = url.substring(7);
        }
        if(url.substring(0,8).equals("https://")) {
            url = url.substring(8);
        }
        if(url.charAt(url.length()-1) == '/') {
            url = url.substring(0,url.length()-1);
        }
        return url;
    }

    static BigInteger urlToShortnedId(String url) {
        BigInteger id = BigInteger.ZERO;

        for(int i=0;i<url.length();i++) {
            if(url.charAt(i) >= 'a' && url.charAt(i) <='z') {
                id = id.multiply(BigInteger.valueOf(62)).add(BigInteger.valueOf(url.charAt(i) - 'a'));
            }
            if (url.charAt(i) >= 'A' && url.charAt(i) <='Z') {
                id = id.multiply(BigInteger.valueOf(62)).add(BigInteger.valueOf(url.charAt(i) - 'A' + 26));
            }
            if(url.charAt(i) >= '0' && url.charAt(i) <= '9') {
                id = id.multiply(BigInteger.valueOf(62)).add(BigInteger.valueOf(url.charAt(i) - '0' + 52));
            }
        }
        return id;
    }
    public static void main(String args[]) {
        String url="https://www.google.com/";
        String newUrl = sanitizeUrl(url);
        System.out.println("Sanitized url: "+newUrl);
        BigInteger id = urlToShortnedId(newUrl);
        System.out.println(urlToShortnedId(newUrl));
        System.out.println("Original URL:" + idToOrigUrl(id));

    }
}