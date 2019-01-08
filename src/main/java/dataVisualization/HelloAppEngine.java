package dataVisualization;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}    
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	String ballotId;
	if(request == null) {
		ballotId = "1";
	}
	else ballotId = request.getParameter("ballotID");	
	
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
	
    //f.delete();
    
    file = new File(ballotId + ".png");
    //file.deleteOnExit();	
	
    response.setContentType("image/png");
    response.getOutputStream().write(Files.readAllBytes(file.toPath()));

  }
}