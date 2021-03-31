import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class HttpClientConnect implements Connect {
    private static final String HOST = "http://jsonplaceholder.typicode.com/posts";

    @Override
    public String get(int id) throws IOException, ParseException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(HOST + "/" + id);
        HttpResponse httpResponse = httpclient.execute(httpGet);

        return JsonUtils.parsingArticleInformation(readResponse(httpResponse), "GET");
    }

    @Override
    public String post(String title) throws IOException, ParseException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(HOST);
        StringEntity params = new StringEntity("{\"title\": \"" + title + "\",\"body\": \"Hello\",\"userId\": 1}");
        httpPost.addHeader("content-type", "application/json");
        httpPost.setEntity(params);
        HttpResponse httpResponse = httpclient.execute(httpPost);

        return JsonUtils.parsingArticleInformation(readResponse(httpResponse), "POST");
    }

    private String readResponse (HttpResponse httpResponse) throws IOException {
        StringBuilder response = new StringBuilder();
        Scanner sc = new Scanner(httpResponse.getEntity().getContent());
        while (sc.hasNext()) {
            response.append(sc.nextLine())
                    .append("\n");
        }
        return response.toString();
    }
}
