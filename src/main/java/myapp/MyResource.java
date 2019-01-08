package testyResty.test01;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.FileUtils;

@Path("/ballotGraph/{ballotId}")
public class MyResource {

    @GET
    @Produces("image/png")
    public Response getIt(@PathParam("ballotId") String ballotId) {
    	File file = null;
    	
    	Curl curl = new Curl("https://gnuplot.000webhostapp.com/ballotResult/"); //TODO
        curl.getData("0.json");//potem ballot id

        File f = new File("out.json");
        String in = null;
        try {
            in = FileUtils.readFileToString(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JsonRead jsonRead = new JsonRead(in);

        Map<String, Double> map = jsonRead.read();

        DrawPlot dp = new DrawPlot(map);
        dp.draw(ballotId);
    	
        f.delete();
        
        file = new File(ballotId + ".png");
        file.deleteOnExit();
        ResponseBuilder response = Response.ok((Object) file);
        
        response.header("Content-Disposition",
                "attachment; filename=" + ballotId + ".png");
        return response.build();
    }
}
