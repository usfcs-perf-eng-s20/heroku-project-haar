package usfca.edu.service.logic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usfca.edu.config.Constants;
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
        long timeDifference = timestampEnd - timestampStart;
        long totalInterval = 1;
        long eachIntervalInMs = 0;
        if (interval.equalsIgnoreCase(Constants.INTERVAL_MINUTES)) {
            totalInterval = timeDifference / Constants.MINUTES_MS;
            eachIntervalInMs = Constants.MINUTES_MS;
        } else if (interval.equalsIgnoreCase(Constants.INTERVAL_HOURS)) {
            totalInterval = timeDifference / Constants.HOURS_MS;
            eachIntervalInMs = Constants.HOURS_MS;
        } else if (interval.equalsIgnoreCase(Constants.INTERVAL_DAYS)) {
            totalInterval = timeDifference / Constants.DAYS_MS;
            eachIntervalInMs = Constants.DAYS_MS;
        } else if (interval.equalsIgnoreCase(Constants.INTERVAL_WEEKS)) {
            totalInterval = timeDifference / Constants.WEEKS_MS;
            eachIntervalInMs = Constants.WEEKS_MS;
        } else {
            /**
             * DEFAULT..
             */
            return getStatsByTimeWithCumulative(null, timestampStart, timestampEnd);
        }

        System.out.println("TimeDifference:" + timeDifference);
        System.out.println("TotalInterval:" + totalInterval);

        if (totalInterval == 0) {
            System.out.println("TotalInterval:" + totalInterval
                    + ", Can not divide into intervals. So calculating cumulative.");
            return getStatsByTimeWithCumulative(null, timestampStart, timestampEnd);
        }

        for (int i = 1; i <= totalInterval; i++) {
            System.out.println("" + i + ". interval...");
            timestampEnd = timestampStart + eachIntervalInMs;

            if (!service.equalsIgnoreCase(Constants.SERVICE_ALL)) {//search,login,favorites
                List<Edr> edrList = edrRepository
                        .findBySpecificTimeByService(service,
                                                     new Timestamp(timestampStart),
                                                     new Timestamp(timestampEnd));
                kpiFormList.addAll(convertIntoOneCumulativeForm(service,
                                                                edrList,
                                                                timestampStart,
                                                                timestampEnd));
                timestampStart = timestampEnd;
            } else {//ALL
                kpiFormList
                        .addAll(getStatsByTimeWithCumulative(null, timestampStart, timestampEnd));
            }
        }
        return kpiFormList;
    }

    public List<StatisticForm> getStatsByTimeWithCumulative(String service, long timestampStart,
                                                            long timestampEnd) {
        List<StatisticForm> kpiFormList = new ArrayList<StatisticForm>();

        if (service == null) {
            List<Edr> edrList = edrRepository.findBySpecificTime(new Timestamp(timestampStart),
                                                                 new Timestamp(timestampEnd));
            System.out.println("edrList Size:" + edrList.size());
            kpiFormList = convertIntoOneCumulativeForm(null, edrList, timestampStart, timestampEnd);
        } else {
            List<Edr> edrList = edrRepository
                    .findBySpecificTimeByService(service,
                                                 new Timestamp(timestampStart),
                                                 new Timestamp(timestampEnd));
            System.out.println("edrList Size:" + edrList.size());
            kpiFormList = convertIntoOneCumulativeForm(null, edrList, timestampStart, timestampEnd);
        }

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
    public List<StatisticForm> convertIntoOneCumulativeForm(String service, List<Edr> edrList,
                                                            long startTime, long endTime) {
        List<StatisticForm> kpiFormList = new ArrayList<StatisticForm>();
        int allErrorCount = 0, searchErrorCount = 0, loginErrorCount = 0, favoriteErrorCount = 0,
                searchRequestCount = 0, loginRequestCount = 0, favoriteRequestCount = 0;

        Date startDate = new Date(new Timestamp(startTime).getTime());
        Date endDate = new Date(new Timestamp(endTime).getTime());

        for (Edr edr : edrList) {
            if (!edr.isSuccess()) {
                allErrorCount++;
            }
            if (edr.getServiceName().equalsIgnoreCase(Constants.SERVICE_SEARCH)) {
                searchRequestCount++;
                if (!edr.isSuccess()) {
                    searchErrorCount++;
                }
            } else if (edr.getServiceName().equalsIgnoreCase(Constants.SERVICE_LOGIN)) {
                loginRequestCount++;
                if (!edr.isSuccess()) {
                    loginErrorCount++;
                }
            } else if (edr.getServiceName().equalsIgnoreCase(Constants.SERVICE_FAVORITES)) {
                favoriteRequestCount++;
                if (!edr.isSuccess()) {
                    favoriteErrorCount++;
                }
            }
        }

        if (service == null) {
            // ALL
            StatisticForm allStatForm = new StatisticForm();
            allStatForm.setServiceName("All");
            allStatForm.setNumApiCalls(edrList.size());
            allStatForm.setError(allErrorCount);
            allStatForm.setStartTime(startDate);
            allStatForm.setEndTime(endDate);

            StatisticForm searchStatForm = new StatisticForm();
            searchStatForm.setServiceName("search");
            searchStatForm.setNumApiCalls(searchRequestCount);
            searchStatForm.setError(searchErrorCount);
            searchStatForm.setStartTime(startDate);
            searchStatForm.setEndTime(endDate);

            StatisticForm loginStatForm = new StatisticForm();
            loginStatForm.setServiceName("login");
            loginStatForm.setNumApiCalls(loginRequestCount);
            loginStatForm.setError(loginErrorCount);
            loginStatForm.setStartTime(startDate);
            loginStatForm.setEndTime(endDate);

            StatisticForm favStatForm = new StatisticForm();
            favStatForm.setServiceName("favorite");
            favStatForm.setNumApiCalls(favoriteRequestCount);
            favStatForm.setError(favoriteErrorCount);
            favStatForm.setStartTime(startDate);
            favStatForm.setEndTime(endDate);

            kpiFormList.add(allStatForm);
            kpiFormList.add(searchStatForm);
            kpiFormList.add(loginStatForm);
            kpiFormList.add(favStatForm);

            return kpiFormList;
        } else {
            StatisticForm statForm = new StatisticForm();
            statForm.setServiceName(service);
            statForm.setNumApiCalls(edrList.size());
            statForm.setError(allErrorCount);
            statForm.setStartTime(startDate);
            statForm.setEndTime(endDate);
            return kpiFormList;
        }
    }

}
