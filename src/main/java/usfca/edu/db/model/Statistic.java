package usfca.edu.db.model;

public class Statistic {
    private long min;
    private long max;
    private double avg;

    public Statistic() {
    }

    public Statistic(Long min, Long max, Double avg) {
        this.min = min == null ? 0 : min;
        this.max = max == null ? 0 : max;
        this.avg = avg == null ? 0 : avg;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "min=" + min +
                ", max=" + max +
                ", avg=" + avg +
                '}';
    }
}
