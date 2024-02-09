import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL(UrlProvider.getUrlFromFile());
            BufferedReader in = getReaderFromUrl(url);

            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            System.out.println(content);

            in.close();
        } catch (MalformedURLException e) {
            System.out.println("Hatalı URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static BufferedReader getReaderFromUrl(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Accept", "application/json");

        con.setConnectTimeout(10000);
        con.setReadTimeout(10000);

        int status = con.getResponseCode();

        BufferedReader in;
        if (status > 299) {
            in = new BufferedReader(
                    new InputStreamReader(con.getErrorStream()));
        } else {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
        }
        return in;
    }
}