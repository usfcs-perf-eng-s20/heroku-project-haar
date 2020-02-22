package usfca.edu.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usfca.edu.db.model.Edr;
import usfca.edu.json.model.EdrForm;
import usfca.edu.persistence.EdrRepository;

import java.util.List;

@Service
public class EdrService implements EdrServiceInterface {

    @Autowired
    private final EdrRepository edrRepository;

    public EdrService(EdrRepository edrRepository) {
        super();
        this.edrRepository = edrRepository;
    }

    @Override
    public boolean saveEdr(EdrForm edrForm) {
        edrRepository.save(new Edr(edrForm));

        return true;
    }

    @Override
    public List<Edr> getAllEdr() {
        return (List<Edr>) edrRepository.findAll();
    }



    @Override
    public List<Edr> getEdrsByServiceName(String serviceName) {
        return (List<Edr>) edrRepository.findByServiceName(serviceName);
    }


}
