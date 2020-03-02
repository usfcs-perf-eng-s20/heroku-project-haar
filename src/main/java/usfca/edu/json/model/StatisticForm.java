package usfca.edu.json.model;

public class StatisticForm {

    private String serviceName = "";

    private int    numApiCalls = 0;

    private int    error       = 0;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getNumApiCalls() {
        return numApiCalls;
    }

    public void setNumApiCalls(int numApiCalls) {
        this.numApiCalls = numApiCalls;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

}
