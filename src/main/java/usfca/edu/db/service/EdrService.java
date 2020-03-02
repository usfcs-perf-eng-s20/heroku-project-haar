package usfca.edu.db.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usfca.edu.db.model.Edr;
import usfca.edu.json.model.EdrForm;
import usfca.edu.json.model.KpiForm;
import usfca.edu.persistence.EdrRepository;

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
    public List<Edr> getEdrByTimeWithInterval(String service, long timestampStart,
                                              long timestampEnd, String interval) {
        List<Edr> edrList = edrRepository.findBySpecificTime(new Timestamp(timestampStart),
                                                             new Timestamp(timestampEnd));

        return edrList;
    }

    @Override
    public List<KpiForm> calculateKpiByTimeWithInterval(String service, long timestampStart,
                                                        long timestampEnd, String interval) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<KpiForm> calculateKpiByTimeWithCumulative(String serviceName, long timestampStart,
                                                          long timestampEnd) {
        List<KpiForm> kpiFormList = null;
        if (serviceName != null && !serviceName.equalsIgnoreCase("all")) {
            List<Edr> edrList = edrRepository
                    .findBySpecificTimeByService(serviceName,
                                                 new Timestamp(timestampStart),
                                                 new Timestamp(timestampEnd));
        } else {
            List<Edr> edrList = edrRepository.findBySpecificTime(new Timestamp(timestampStart),
                                                                 new Timestamp(timestampEnd));

        }

        return kpiFormList;

    }

}
