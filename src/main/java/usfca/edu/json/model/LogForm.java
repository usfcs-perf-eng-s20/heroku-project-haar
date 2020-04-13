package usfca.edu.json.model;

public class LogForm {
    public LogForm() {
    }

    public LogForm(String serviceName, long runtime, boolean error, String message, String endpoint) {
        this.serviceName = serviceName;
        this.runtime = runtime;
        this.error = error;
        this.message = message;
        this.endpoint = endpoint;
        this.custom = null;
    }

    public LogForm(String serviceName, long runtime, boolean error, String message, String endpoint, Object custom) {
        this.serviceName = serviceName;
        this.runtime = runtime;
        this.error = error;
        this.message = message;
        this.endpoint = endpoint;
        this.custom = custom;
    }

    String serviceName;
    long runtime;
    boolean error;
    String message;
    String endpoint;
    Object custom;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        String customString = custom != null ? custom.toString() : "";
        return "serviceName='" + serviceName + "\'"
                + " runtime=" + runtime
                + " error=" + error
                + " message='" + message + "\'"
                + " endpoint='" + endpoint + "\'"
                + " " + customString;
    }
}
