package usfca.edu.db.model;

public class Statistic {
    private long min;
    private long max;
    private double avg;

    public Statistic() {
    }

    public Statistic(long min, long max, double avg) {
        this.min = min;
        this.max = max;
        this.avg = avg;
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
}
