package dataVisualization;

import java.io.IOException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	Curl curl = new Curl("https://gnuplot.000webhostapp.com/ballotResult/"); //TODO

    JsonRead jsonRead = new JsonRead(curl.getData("0.json"));

    Map<String, Double> map = jsonRead.read();

    DrawPlot dp = new DrawPlot(map);
	
    response.setContentType("image/png");
    response.getOutputStream().write(dp.draw(ballotId));

  }
}