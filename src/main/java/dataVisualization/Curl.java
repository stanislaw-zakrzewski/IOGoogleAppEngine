package dataVisualization;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;

public class Curl {
    private String serverPath;

    public Curl(String serverPath) {
        this.serverPath = serverPath;
    }

    public InputStream getData(String id) {
        String charset = "UTF-8";
        String param1 = "--location";
        String param2 = "--request GET";
        //String param3 = "--header Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6MTU0NDk3ODY4NywiZXhwIjoxNTQ0OTgwNDg3fQ._Hof0fepC0fwVBIU-t7oBZC8fm9M0c6jBE6EVWP8wVA";
        String query = null;
        URLConnection connection = null;
        InputStream response = null;
        InputStream result = null;

        try {
            query = String.format("param1=%s&param2=%s",
                    URLEncoder.encode(param1, charset),
                    URLEncoder.encode(param2, charset));
                    //URLEncoder.encode(param3, charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            connection = new URL(serverPath + id + "?" + query).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection.setRequestProperty("Accept-Charset", charset);

        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = response;

        File file = new File("out.json");
        if (!file.exists()) {
            try {
                Files.copy(response, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}