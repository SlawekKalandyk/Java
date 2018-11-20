package Mainpack;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class Histogram {
    private XYChart.Series series = new XYChart.Series();
    private ArrayList<Integer> counts = new ArrayList<>();
    private ArrayList<Double> dataList = new ArrayList<>();
    private Integer columns = 10;
    private Double minData = 0.0;
    private Double maxData = 0.0;
    private ArrayList<Double> intervals = new ArrayList<>();

    public Histogram() {

    }

    public void fillHistogramWithData() {
        maxData = dataList.get(dataList.size() - 1);
        minData = dataList.get(0);
        Double singleColumn = Math.ceil((maxData + 1 - minData) / columns);
        intervals.clear();
        intervals.add(minData);

        for (int i = 1; i <= columns; ++i) {
            Double temp = intervals.get((i - 1) * 2) + singleColumn;
            intervals.add(temp - 1);
            intervals.add(temp);
        }
        intervals.remove(intervals.size() - 1);

        counts.clear();
        for (int i = 0; i < columns; ++i)
            counts.add(0);

        int j = 0;
        for (int i = 1; i < intervals.size(); i += 2) {
            int counter = 0;
            while (j < dataList.size() && i < intervals.size()
                    && dataList.get(j) <= intervals.get(i)) {
                ++counter;
                ++j;
            }
            counts.set(i / 2, counts.get(i / 2) + counter);
        }

        updateChart();
    }

    public void insertIntoHistogram(Double input) {
        maxData = dataList.get(dataList.size() - 1);
        minData = dataList.get(0);

        int i = 0;
        while (input > intervals.get(i))
            ++i;
        counts.set(i / 2, counts.get(i / 2) + 1);

        updateChart();
    }

    public void updateChart() {
        series.getData().clear();
        for (int i = 1; i < intervals.size(); i += 2) {
            String interval = intervals.get(i - 1).toString() +
                    " - " + intervals.get(i).toString();
            series.getData().add(new XYChart.Data<>(interval, counts.get((i - 1) / 2)));
        }
    }

    public void removeFromHistogram(int index) {
        if(dataList.size() > 1) {
            if (index == dataList.size() - 1)
                maxData = dataList.get(dataList.size() - 2);
            else if (index == 0)
                minData = dataList.get(1);

            int i = 0;
            while (dataList.get(index) > intervals.get(i))
                ++i;
            counts.set(i / 2, counts.get(i / 2) - 1);
            dataList.remove(index);

            fillHistogramWithData();
        } else {
            dataList.remove(index);
            maxData = 0.0;
            minData = 0.0;
            intervals.clear();
            counts.clear();
            updateChart();
        }

    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public XYChart.Series getSeries() {
        return series;
    }

    public void setSeries(XYChart.Series series1) {
        this.series = series1;
    }


    public ArrayList<Double> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<Double> dataList) {
        this.dataList = dataList;
        fillHistogramWithData();
    }

    public Double getMinData() {
        return minData;
    }

    public void setMinData(Double minData) {
        this.minData = minData;
    }

    public Double getMaxData() {
        return maxData;
    }

    public void setMaxData(Double maxData) {
        this.maxData = maxData;
    }

    public ArrayList<Integer> getCounts() {
        return counts;
    }

    public void setCounts(ArrayList<Integer> counts) {
        this.counts = counts;
    }
}
