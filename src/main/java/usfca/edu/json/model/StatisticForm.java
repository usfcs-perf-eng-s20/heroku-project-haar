package usfca.edu.json.model;

import java.util.Date;

public class StatisticForm {

    public StatisticForm() {
    }

    public StatisticForm(Date startTime, Date endTime, String serviceName, long numApiCalls, long error) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.serviceName = serviceName;
        this.numApiCalls = numApiCalls;
        this.error = error;
    }

    private Date   startTime;

    private Date   endTime;

    private String serviceName = "";

    private long    numApiCalls = 0;

    private long    error       = 0;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getNumApiCalls() {
        return numApiCalls;
    }

    public void setNumApiCalls(int numApiCalls) {
        this.numApiCalls = numApiCalls;
    }

    public long getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
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

}
