package dataVisualization;

import java.awt.Color;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class DrawPlot {
    private Map<String, Double> data;

    public DrawPlot(Map<String, Double> data) {
        this.data=data;
    }

    public byte[] draw(String fileName) {

        List<Double> glosy = new ArrayList<>();
        glosy.addAll(data.values());

        List<String> nazwiska = new ArrayList<>();
        nazwiska.addAll(data.keySet());

        List<Double> numbers = new ArrayList<>();
        for (int i=1;i<=nazwiska.size();i++) {
            numbers.add(Double.valueOf(String.valueOf(i)));
        }
        Plot plot = Plot.plot(Plot.plotOpts().gridColor(Color.WHITE)
            .title("Głosy na kandydatów")
            .legend(Plot.LegendFormat.BOTTOM));

        plot.xAxis("", Plot.axisOpts().range(0,Collections.max(numbers)+1))
        .yAxis("Ilość głosów", Plot.axisOpts().range(0, Collections.max(glosy)+10));

        for (int i=0;i<nazwiska.size();i++) {
            plot.series(nazwiska.get(i),
                    Plot.data()
                            .xy(numbers.get(i), glosy.get(i)),
                    Plot.seriesOpts().line(Plot.Line.NONE).marker(Plot.Marker.COLUMN)
            );
        }
        try {
			return plot.save2(fileName, "png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
    }
}