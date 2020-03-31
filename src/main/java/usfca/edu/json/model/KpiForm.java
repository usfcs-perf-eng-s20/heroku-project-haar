package usfca.edu.json.model;

import java.util.Date;

public class KpiForm {

    private Date   startTime;

    private Date   endTime;

    private String serviceName;

    private double avgRespTime;

    private long minRespTime;

    private long maxRespTime;

    private double avgRpsTime;

    private double minRpsTime;

    private double maxRpsTime;

    public KpiForm() {
    }

    public KpiForm(Date startTime, Date endTime, String serviceName, double avgRespTime, long minRespTime, long maxRespTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceName = serviceName;
        this.avgRespTime = avgRespTime;
        this.minRespTime = minRespTime;
        this.maxRespTime = maxRespTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getAvgRespTime() {
        return avgRespTime;
    }

    public void setAvgRespTime(double avgRespTime) {
        this.avgRespTime = avgRespTime;
    }

    public long getMinRespTime() {
        return minRespTime;
    }

    public void setMinRespTime(long minRespTime) {
        this.minRespTime = minRespTime;
    }

    public long getMaxRespTime() {
        return maxRespTime;
    }

    public void setMaxRespTime(long maxRespTime) {
        this.maxRespTime = maxRespTime;
    }

    public double getAvgRpsTime() {
        return avgRpsTime;
    }

    public void setAvgRpsTime(double avgRpsTime) {
        this.avgRpsTime = avgRpsTime;
    }

    public double getMinRpsTime() {
        return minRpsTime;
    }

    public void setMinRpsTime(double minRpsTime) {
        this.minRpsTime = minRpsTime;
    }

    public double getMaxRpsTime() {
        return maxRpsTime;
    }

    public void setMaxRpsTime(double maxRpsTime) {
        this.maxRpsTime = maxRpsTime;
    }

    @Override
    public String toString() {
        return "KpiForm{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", serviceName='" + serviceName + '\'' +
                ", avgRespTime=" + avgRespTime +
                ", minRespTime=" + minRespTime +
                ", maxRespTime=" + maxRespTime +
                ", avgRpsTime=" + avgRpsTime +
                ", minRpsTime=" + minRpsTime +
                ", maxRpsTime=" + maxRpsTime +
                '}';
    }
}
