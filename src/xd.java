/**
 * 
 */
/**
 * @author facha
 *
 */
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class xd{
    public static void mains(String[] args) {
        try {
            URL url = new URL("https://www.google.com/search?q=1USD+to+EUR");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println(content.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
