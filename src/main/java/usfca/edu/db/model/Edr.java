package usfca.edu.db.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import usfca.edu.json.model.EdrForm;

@Entity
public class Edr {

    @Id
    @GeneratedValue
    private Long      id;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "processingTimeInMiliseconds")
    private long      processingTimeInMiliseconds;

    @Column(name = "serviceName")
    private String    serviceName;

    @Column(name = "path")
    private String    path;

    @Column(name = "method")
    private String    method;

    @Column(name = "username")
    private String    username;

    @Column(name = "responseCode")
    private String    responseCode;

    @Column(name = "success")
    private boolean   success;

    public Edr() {

    }

    public Edr(EdrForm edrForm) {
        super();
        try {
            this.timestamp = Timestamp.valueOf(edrForm.getTimestamp());
        } catch (Exception e) {
            this.timestamp = new Timestamp(System.currentTimeMillis());
        }
        this.processingTimeInMiliseconds = edrForm.getProcessingTimeInMiliseconds();
        this.serviceName = edrForm.getServiceName();
        this.path = edrForm.getPath();
        this.method = edrForm.getMethod();
        this.username = edrForm.getUsername();
        this.responseCode = edrForm.getResponseCode();
        this.success = edrForm.getSuccess();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getProcessingTimeInMiliseconds() {
        return processingTimeInMiliseconds;
    }

    public void setProcessingTimeInMiliseconds(long processingTimeInMiliseconds) {
        this.processingTimeInMiliseconds = processingTimeInMiliseconds;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
