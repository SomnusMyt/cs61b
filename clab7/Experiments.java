import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        Random r = new Random();
        BST<Integer> test = new BST<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        xValues.add(0);
        yValues.add(0.0);
        y2Values.add(0.0);
        for (int x = 0; x < 5000 ; x += 1) {
            int temp = RandomGenerator.getRandomInt(10000);
            test.add(temp);
            xValues.add(x);
            yValues.add(test.averageDepth());
            y2Values.add(ExperimentHelper.optimalAverageDepth(x));
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("x + random(0, 10)", xValues, yValues);
        chart.addSeries("100*Math.sin(x)", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        Random r = new Random();
        BST<Integer> test = new BST<>();
        for (int x = 0; x < 1000 ; x += 1) {
            int temp = RandomGenerator.getRandomInt(10000);
            test.add(temp);
        }
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        xValues.add(0);
        yValues.add(test.averageDepth());
        for (int x = 0; x < 9000 ; x += 1) {
            test.deleteTakingSuccessor(test.getRandomKey());
            int temp = RandomGenerator.getRandomInt(10000);
            test.add(temp);
            xValues.add(x);
            yValues.add(test.averageDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        Random r = new Random();
        BST<Integer> test = new BST<>();
        for (int x = 0; x < 5000 ; x += 1) {
            int temp = RandomGenerator.getRandomInt(20000);
            test.add(temp);
        }
        List<Double> yValues = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        xValues.add(0);
        yValues.add(test.averageDepth());
        for (int x = 0; x < 15000 ; x += 1) {
            test.deleteTakingRandom(test.getRandomKey());
            int temp = RandomGenerator.getRandomInt(20000);
            test.add(temp);
            xValues.add(x);
            yValues.add(test.averageDepth());
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Real depth", xValues, yValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        //experiment1();
        experiment2();
        //experiment3();
    }
}
