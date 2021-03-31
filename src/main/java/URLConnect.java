import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class URLConnect implements Connect {
    private static final String HOST = "http://jsonplaceholder.typicode.com/posts";
    private static final String POST_PARAMETERS = "userId=1&body=hello&title=";
    private static final String GET = "GET";
    private static final String POST = "POST";


    @Override
    public String get(int id) throws IOException, ParseException {
        HttpURLConnection connect = (HttpURLConnection) new URL(HOST + "/" + id).openConnection();
        connect.setRequestMethod(GET);
        String result = null;
        int responseCode = connect.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(connect.getInputStream()))) {
               result = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
        return JsonUtils.parsingArticleInformation(result, GET);
    }

    @Override
    public String post(String title) throws IOException, ParseException {
        HttpURLConnection connect = (HttpURLConnection) new URL(HOST).openConnection();
        connect.setRequestMethod(POST);
        connect.setDoOutput(true);
        OutputStream outputStream = connect.getOutputStream();
        outputStream.write((POST_PARAMETERS + title).getBytes());
        outputStream.flush();
        outputStream.close();
        String result = null;
        int responseCode = connect.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connect.getInputStream()))) {
                result = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
        return JsonUtils.parsingArticleInformation(result, POST);
    }
}
