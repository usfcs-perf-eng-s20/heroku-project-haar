package usfca.edu.persistence;

import org.springframework.data.repository.CrudRepository;

import usfca.edu.db.model.Edr;

public interface EdrRepository extends CrudRepository<Edr, Long> {

}
