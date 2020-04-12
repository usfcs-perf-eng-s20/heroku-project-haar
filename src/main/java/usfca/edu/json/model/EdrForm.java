package usfca.edu.json.model;

public class EdrForm {

    private String  serviceName;

    private String  path;

    private String  timestamp;

    private String  method;                      //GET POST 

    private String  username;

    private long    processingTimeInMiliseconds; //

    private String  responseCode;                // 403 Forbidden , 401 UnAuthrozied , 404 Not Found:

    //Ex: Search Team Fail: Internal Error Ex: Login Authentication?? 
    private boolean success;                     //YES!! Exception!!

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

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getProcessingTimeInMiliseconds() {
        return processingTimeInMiliseconds;
    }

    public void setProcessingTimeInMiliseconds(long processingTimeInMiliseconds) {
        this.processingTimeInMiliseconds = processingTimeInMiliseconds;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "EdrForm{" +
                "serviceName='" + serviceName + '\'' +
                ", path='" + path + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", method='" + method + '\'' +
                ", username='" + username + '\'' +
                ", processingTimeInMiliseconds=" + processingTimeInMiliseconds +
                ", responseCode='" + responseCode + '\'' +
                ", success=" + success +
                '}';
    }
}
