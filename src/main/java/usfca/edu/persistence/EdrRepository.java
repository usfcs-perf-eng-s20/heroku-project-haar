package usfca.edu.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import usfca.edu.db.model.Edr;

import java.sql.Timestamp;
import java.util.List;

public interface EdrRepository extends CrudRepository<Edr, Long> {

    @Query(value="SELECT e FROM edr e WHERE e.timestamp\\:\\:date = (?1)\\:\\:date",nativeQuery=true)
//    @Query(value="select * from orders where created_date  < clock_timestamp() - ( :toTime )\\:\\:interval",nativeQuery=true)
//    @Query("SELECT e FROM edr e WHERE timestamp >= :timestamp")
    public List<Edr> findBySpecificDate(Timestamp timestamp);
}