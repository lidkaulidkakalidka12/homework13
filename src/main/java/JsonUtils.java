import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public final class JsonUtils {
    private JsonUtils() {
    }

    public static String parsingArticleInformation(String response, String requestMethod) throws ParseException {
        JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(response);
        StringBuilder articleInformation = new StringBuilder();
        articleInformation.append("Article [")
                .append(jsonObject.get("id"));
        if (requestMethod.equals("GET")) {
            articleInformation.append("]: User [");
        } else {
            articleInformation.append("] has been created: User [");
        }
        articleInformation.append(jsonObject.get("userId"))
                .append("] Title [")
                .append(jsonObject.get("title"))
                .append("] Message [")
                .append(jsonObject.get("body"))
                .append("]");
        return articleInformation.toString();
    }
}
