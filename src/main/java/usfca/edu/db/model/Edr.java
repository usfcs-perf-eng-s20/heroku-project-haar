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

    @Column
    private Timestamp timestamp;

    @Column
    private String    microServiceName;

    @Column
    private long      processingTimeInMiliseconds;

    @Column
    private String    serviceName;

    @Column
    private String    path;

    @Column
    private String    method;

    @Column
    private String    username;

    @Column
    private long      processingTime;

    @Column
    private String    responseCode;

    @Column
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
        this.microServiceName = edrForm.getServiceName();
        this.processingTimeInMiliseconds = edrForm.getProcessingTime();
        this.serviceName = edrForm.getServiceName();
        this.path = edrForm.getPath();
        this.method = edrForm.getMethod();
        this.username = edrForm.getUsername();
        this.processingTime = edrForm.getProcessingTime();
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

    public String getMicroServiceName() {
        return microServiceName;
    }

    public void setMicroServiceName(String microServiceName) {
        this.microServiceName = microServiceName;
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

    public long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
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
