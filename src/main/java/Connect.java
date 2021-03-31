import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;

public interface Connect {
    String get(int id) throws IOException, ParseException;
    String post(String title) throws IOException, ParseException;
}
