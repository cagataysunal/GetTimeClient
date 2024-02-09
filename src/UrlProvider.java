import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UrlProvider {
    public static String getUrlFromFile() throws FileNotFoundException {
        File myObj = new File("url.txt");
        Scanner myReader = new Scanner(myObj);
        return myReader.nextLine();
    }
}
