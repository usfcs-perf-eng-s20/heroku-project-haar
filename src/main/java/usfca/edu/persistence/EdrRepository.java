package usfca.edu.persistence;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import usfca.edu.db.model.Edr;

@Repository
public interface EdrRepository extends JpaRepository<Edr, Long> {

    @Query("SELECT e FROM Edr e WHERE e.serviceName = :serviceName")
    public List<Edr> findByServiceName(@Param("serviceName") String serviceName);

    @Query("SELECT e FROM Edr e WHERE e.timestamp >= :timestampStart and e.timestamp < :timestampEnd")
    public List<Edr> findBySpecificTime(Timestamp timestampStart, Timestamp timestampEnd);

}
