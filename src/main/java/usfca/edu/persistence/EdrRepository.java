package usfca.edu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import usfca.edu.db.model.Edr;

import java.util.List;

@Repository
public interface EdrRepository extends JpaRepository<Edr, Long> {

    @Query("SELECT e FROM Edr e WHERE e.serviceName = :serviceName")
    public List<Edr> findByServiceName(@Param("serviceName") String serviceName);

}
