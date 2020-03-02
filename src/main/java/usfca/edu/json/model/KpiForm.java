package usfca.edu.json.model;

public class KpiForm {

    private String serviceName;

    private int    avgRespTime;

    private int    minRespTime;

    private int    maxRespTime;

    private int    avgRpsTime;

    private int    minRpsTime;

    private int    maxRpsTime;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getAvgRespTime() {
        return avgRespTime;
    }

    public void setAvgRespTime(int avgRespTime) {
        this.avgRespTime = avgRespTime;
    }

    public int getMinRespTime() {
        return minRespTime;
    }

    public void setMinRespTime(int minRespTime) {
        this.minRespTime = minRespTime;
    }

    public int getMaxRespTime() {
        return maxRespTime;
    }

    public void setMaxRespTime(int maxRespTime) {
        this.maxRespTime = maxRespTime;
    }

    public int getAvgRpsTime() {
        return avgRpsTime;
    }

    public void setAvgRpsTime(int avgRpsTime) {
        this.avgRpsTime = avgRpsTime;
    }

    public int getMinRpsTime() {
        return minRpsTime;
    }

    public void setMinRpsTime(int minRpsTime) {
        this.minRpsTime = minRpsTime;
    }

    public int getMaxRpsTime() {
        return maxRpsTime;
    }

    public void setMaxRpsTime(int maxRpsTime) {
        this.maxRpsTime = maxRpsTime;
    }

}
