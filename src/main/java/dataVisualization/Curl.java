package dataVisualization;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Curl {
    private String serverPath;

    public Curl(String serverPath) {
        this.serverPath = serverPath;
    }

    public String getData(String id) {
        String charset = "UTF-8";
        String param1 = "--location";
        String param2 = "--request GET";
        //String param3 = "--header Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsImlhdCI6MTU0NDk3ODY4NywiZXhwIjoxNTQ0OTgwNDg3fQ._Hof0fepC0fwVBIU-t7oBZC8fm9M0c6jBE6EVWP8wVA";
        String query = null;
        URLConnection connection = null;
        InputStream response = null;
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

        StringBuilder stringBuilder = new StringBuilder();
    	String line = null;
    	
    	/*try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response, charset))) {	
    		while ((line = bufferedReader.readLine()) != null) {
    			stringBuilder.append(line);
    		}
    	} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(stringBuilder.toString());*/
     
    	return "{  \"ballotResult\": [   {     \"name\": \"A\",     \"votes\": \"10\",     \"id\": 0   },   {     \"name\": \"B\",     \"votes\": \"20\",     \"id\": 1   },   {     \"name\": \"C\",     \"votes\": \"15\",     \"id\": 2   },   {     \"name\": \"D\",     \"votes\": \"40\",     \"id\": 3   },   {     \"name\": \"E\",     \"votes\": \"10\",     \"id\": 4   },   {     \"name\": \"F\",     \"votes\": \"35\",     \"id\": 5   },   {     \"name\": \"G\",     \"votes\": \"5\",     \"id\": 6   },   {     \"name\": \"H\",     \"votes\": \"30\",     \"id\": 7   }, ]}";
    }

}