package GraficoDeLinha;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class GraficoLinha {
    public static void main(String[] args){
        var alunos = new XYSeries("Alunos");
        var notas = new XYSeries("Notas");

        try {
            FileReader fr = new FileReader(""); //caminho do arquivo
            BufferedReader bf = new BufferedReader(fr);
            String linha = bf.readLine();
//            System.out.println(linha);
            int contador=1;
            while (contador<=31){
                String[] dados=linha.split(";");
                notas.add(contador, Double.parseDouble(dados[4].replace(",",".")));
                System.out.println(dados[0] + " - RA: " + dados[1]);
                System.out.println(dados[4]);
                linha = bf.readLine();
                contador++;
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Diretorio de trabalho"+System.getProperty("user.dir"));
        }

        var dataset=new XYSeriesCollection();
        dataset.addSeries(alunos);
        dataset.addSeries(notas);


        JFreeChart chart = ChartFactory.createXYLineChart(
                "Médias da notas semestral",
                "Alunos",
                "Notas",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        XYPlot plot = chart.getXYPlot();
        var renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesStroke(0, new BasicStroke(5.0f));
        renderer.setSeriesPaint(1, Color.blue);
        renderer.setSeriesStroke(1, new BasicStroke(5.0f));


        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setForegroundAlpha(0.9f);
        plot.setRangeGridlinePaint(Color.red);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.black);
        plot.setDomainGridlinesVisible(true);



        chart.getLegend().setFrame(BlockBorder.NONE);

        ChartFrame frame1 = new ChartFrame("Gráfico de linhas", chart);
        frame1.setVisible(true);
        frame1.setSize(1380, 800);




    }
}
