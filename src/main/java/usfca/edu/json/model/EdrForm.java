package usfca.edu.json.model;

public class EdrForm {

    private String     serviceName;

    private String     path;

    private String     timestamp;

    private String     method;

    private Parameters parameters;

    private String     username;

    private long       processingTimeInMiliseconds;

    private String     responseCode;

    private boolean    success;

    // Getter Methods 
    public String getServiceName() {
        return serviceName;
    }

    public String getPath() {
        return path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMethod() {
        return method;
    }

    public String getUsername() {
        return username;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public boolean getSuccess() {
        return success;
    }

    // Setter Methods 

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getProcessingTimeInMiliseconds() {
        return processingTimeInMiliseconds;
    }

    public void setProcessingTimeInMiliseconds(long processingTimeInMiliseconds) {
        this.processingTimeInMiliseconds = processingTimeInMiliseconds;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
}
