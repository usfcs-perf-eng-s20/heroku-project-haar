package usfca.edu.db.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private String    message;

    public Edr() {

    }

    public Edr(Timestamp timestamp, String microServiceName, long processingTimeInMiliseconds,
               String message) {
        super();
        this.timestamp = timestamp;
        this.microServiceName = microServiceName;
        this.processingTimeInMiliseconds = processingTimeInMiliseconds;
        this.message = message;
    }

    public Edr(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

}
