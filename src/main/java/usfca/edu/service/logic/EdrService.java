package usfca.edu.service.logic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usfca.edu.db.model.Edr;
import usfca.edu.json.model.EdrForm;
import usfca.edu.json.model.KpiForm;
import usfca.edu.json.model.StatisticForm;
import usfca.edu.persistence.EdrRepository;

@Service
public class EdrService {

    @Autowired
    private final EdrRepository edrRepository;

    public EdrService(EdrRepository edrRepository) {
        super();
        this.edrRepository = edrRepository;
    }

    public boolean saveEdr(EdrForm edrForm) {
        edrRepository.save(new Edr(edrForm));

        return true;
    }

    public List<StatisticForm> getStatsByTimeWithInterval(String service, long timestampStart,
                                                          long timestampEnd, String interval) {
        List<StatisticForm> kpiFormList = new ArrayList<StatisticForm>();
        List<Edr> edrList = edrRepository.findBySpecificTime(new Timestamp(timestampStart),
                                                             new Timestamp(timestampEnd));

        return kpiFormList;
    }

    public List<StatisticForm> getStatsByTimeWithCumulative(long timestampStart,
                                                            long timestampEnd) {
        List<StatisticForm> kpiFormList = new ArrayList<StatisticForm>();
        List<Edr> edrList = edrRepository.findBySpecificTime(new Timestamp(timestampStart),
                                                             new Timestamp(timestampEnd));
        int allErrorCount = 0, searchErrorCount = 0, loginErrorCount = 0, favoriteErrorCount = 0,
                searchRequestCount = 0, loginRequestCount = 0, favoriteRequestCount = 0;

        for (Edr edr : edrList) {
            if (!edr.isSuccess()) {
                allErrorCount++;
            }
            if (edr.getServiceName().equalsIgnoreCase("search")) {
                searchRequestCount++;
                if (!edr.isSuccess()) {
                    searchErrorCount++;
                }
            } else if (edr.getServiceName().equalsIgnoreCase("login")) {
                loginRequestCount++;
                if (!edr.isSuccess()) {
                    loginErrorCount++;
                }
            } else if (edr.getServiceName().equalsIgnoreCase("favorite")) {
                favoriteRequestCount++;
                if (!edr.isSuccess()) {
                    favoriteErrorCount++;
                }
            }
        }

        // ALL
        StatisticForm allStatForm = new StatisticForm();
        allStatForm.setServiceName("All");
        allStatForm.setNumApiCalls(edrList.size());
        allStatForm.setError(allErrorCount);

        StatisticForm searchStatForm = new StatisticForm();
        searchStatForm.setServiceName("search");
        searchStatForm.setNumApiCalls(searchRequestCount);
        searchStatForm.setError(searchErrorCount);

        StatisticForm loginStatForm = new StatisticForm();
        loginStatForm.setServiceName("login");
        loginStatForm.setNumApiCalls(loginRequestCount);
        loginStatForm.setError(loginErrorCount);

        StatisticForm favStatForm = new StatisticForm();
        favStatForm.setServiceName("favorite");
        favStatForm.setNumApiCalls(favoriteRequestCount);
        favStatForm.setError(favoriteErrorCount);

        kpiFormList.add(allStatForm);
        kpiFormList.add(searchStatForm);
        kpiFormList.add(loginStatForm);
        kpiFormList.add(favStatForm);

        return kpiFormList;
    }

    public List<KpiForm> calculateKpiByTimeWithInterval(String service, long timestampStart,
                                                        long timestampEnd, String interval) {
        // TODO Auto-generated method stub
        return null;
    }

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

            System.out
                    .print("Calculate cumulative KPI values for all services in a given StartTime:"
                            + timestampStart + ",EndTime:" + timestampEnd);

            /**
             * return cumulative KPI values as a form.
             */
            kpiFormList = convertIntoOneCumulativeEdrForm(edrList);

        }

        return kpiFormList;

    }

    /**
     * 
     * 
     * 
     * @param edrList
     * @return
     */
    public List<KpiForm> convertIntoOneCumulativeEdrForm(List<Edr> edrList) {
        List<KpiForm> kpiFormList = null;

        double totalRespTime = 0;
        double totalRpsTime = 0;
        double maxRespTime = Integer.MIN_VALUE;
        double maxRpsTime = Integer.MIN_VALUE;
        double minRespTime = Integer.MAX_VALUE;
        double minRpsTime = Integer.MAX_VALUE;

        for (Edr edr : edrList) {

            //edr.get

        }

        KpiForm kpiForm = new KpiForm();

        //        kpiForm.setAvgRespTime(avgRespTime);
        //        kpiForm.setAvgRpsTime(avgRpsTime);
        //        kpiForm.setMaxRespTime(maxRespTime);
        //        kpiForm.setMaxRpsTime(maxRpsTime);
        //        kpiForm.setMinRespTime(minRespTime);
        //        kpiForm.setMinRpsTime(minRpsTime);

        return kpiFormList;
    }

}
