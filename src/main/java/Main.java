import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        Connect connect = null;

        switch (args[0]) {
            case "task1":
                connect = new URLConnect();
                break;
            case "task2":
                connect = new HttpClientConnect();
                break;
        }
        methodSelection(connect, args);
    }

    private static void methodSelection(Connect connect, String[] args) throws IOException, ParseException {
        if (args[1].equals("GET")) {
            System.out.println(connect.get(Integer.parseInt(args[2])));
        }
        if (args[1].equals("POST")) {
            System.out.println(connect.post(args[2]));
        }
    }
}