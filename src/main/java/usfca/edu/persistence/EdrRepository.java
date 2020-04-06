package usfca.edu.persistence;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usfca.edu.db.model.CountForm;
import usfca.edu.db.model.Edr;
import usfca.edu.db.model.Statistic;

@Repository
public interface EdrRepository extends JpaRepository<Edr, Long> {

    @Query("SELECT e FROM Edr e WHERE e.serviceName = :serviceName")
    public List<Edr> findByServiceName(@Param("service") String serviceName);

    @Query("SELECT e FROM Edr e WHERE e.timestamp >= :timestampStart and e.timestamp < :timestampEnd")
    public List<Edr> findBySpecificTime(Timestamp timestampStart, Timestamp timestampEnd);

    @Query("SELECT e FROM Edr e WHERE e.timestamp >= :timestampStart and e.timestamp < :timestampEnd"
            + " and e.serviceName = :serviceName ")
    public List<Edr> findBySpecificTimeByService(String serviceName, Timestamp timestampStart,
                                                 Timestamp timestampEnd);

    @Query("SELECT COUNT(e) FROM Edr e WHERE e.timestamp >= :timestampStart and e.timestamp < :timestampEnd"
            + " and e.serviceName = :serviceName and e.success = :success")
    public int findBySpecificTimeByServiceAndSuccess(String serviceName, Timestamp timestampStart,
                                                 Timestamp timestampEnd, boolean success);

    @Query("SELECT COUNT(e) FROM Edr e WHERE e.timestamp >= :timestampStart and e.timestamp < :timestampEnd"
            + " and e.success = :success")
    public int findBySpecificTimeBySuccess(Timestamp timestampStart, Timestamp timestampEnd,
                                            boolean success);

    @Query("SELECT new usfca.edu.db.model.CountForm(COUNT(e), e.success) " +
            "FROM Edr e " +
            "WHERE e.timestamp >= :timestampStart and e.timestamp < :timestampEnd GROUP BY e.success")
    public List<CountForm> getCountApi(Timestamp timestampStart, Timestamp timestampEnd);

    @Query("SELECT new usfca.edu.db.model.CountForm(COUNT(e), e.success) " +
            "FROM Edr e " +
            "WHERE e.timestamp >= :timestampStart and e.timestamp < :timestampEnd and e.serviceName=:serviceName " +
            "GROUP BY e.success")
    public List<CountForm> getCountApiByServiceName(String serviceName, Timestamp timestampStart, Timestamp timestampEnd);

    @Query("SELECT new usfca.edu.db.model.Statistic(MIN(e.processingTimeInMiliseconds) ,MAX(e.processingTimeInMiliseconds), AVG(e.processingTimeInMiliseconds))" +
            "FROM Edr e " +
            "WHERE e.timestamp >= :timestampStart and e.timestamp < :timestampEnd")
    public Statistic findKpiByTimestamp(Timestamp timestampStart, Timestamp timestampEnd);

    @Query("SELECT new usfca.edu.db.model.Statistic(MIN(e.processingTimeInMiliseconds) ,MAX(e.processingTimeInMiliseconds), AVG(e.processingTimeInMiliseconds))" +
            "FROM Edr e " +
            "WHERE e.timestamp >= :timestampStart and e.timestamp < :timestampEnd AND e.serviceName = :serviceName")
    public Statistic findKpiByTimestampAndService(String serviceName, Timestamp timestampStart, Timestamp timestampEnd);

}
