package usfca.edu.json.model;

import java.util.Date;

public class KpiForm {

    private Date   startTime;

    private Date   endTime;

    private String serviceName;

    private double avgRespTime;

    private double minRespTime;

    private double maxRespTime;

    private double avgRpsTime;

    private double minRpsTime;

    private double maxRpsTime;

    public KpiForm() {
    }

    public KpiForm(Date startTime, Date endTime, String serviceName, double avgRespTime, double minRespTime, double maxRespTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceName = serviceName;
        this.avgRespTime = avgRespTime;
        this.minRespTime = minRespTime;
        this.maxRespTime = maxRespTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public double getAvgRespTime() {
        return avgRespTime;
    }

    public void setAvgRespTime(double avgRespTime) {
        this.avgRespTime = avgRespTime;
    }

    public double getMinRespTime() {
        return minRespTime;
    }

    public void setMinRespTime(double minRespTime) {
        this.minRespTime = minRespTime;
    }

    public double getMaxRespTime() {
        return maxRespTime;
    }

    public void setMaxRespTime(double maxRespTime) {
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

}
