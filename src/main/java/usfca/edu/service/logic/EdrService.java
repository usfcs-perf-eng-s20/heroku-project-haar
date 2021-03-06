package usfca.edu.service.logic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import usfca.edu.config.Constants;
import usfca.edu.controller.AnalyticsController;
import usfca.edu.db.model.CountForm;
import usfca.edu.db.model.Edr;
import usfca.edu.db.model.Statistic;
import usfca.edu.json.model.EdrForm;
import usfca.edu.json.model.KpiForm;
import usfca.edu.json.model.StatisticForm;
import usfca.edu.persistence.EdrRepository;

@Service
public class EdrService {

    private final Logger logger = LoggerFactory.getLogger(EdrService.class);
    
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

        logger.debug("EdrService.getStatsByTimeWithInterval.");

        List<StatisticForm> statFormList = new ArrayList<StatisticForm>();
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

        logger.debug("TimeDifference:" + timeDifference);
        logger.debug("TotalInterval:" + totalInterval);

        if (totalInterval == 0) {
            logger.debug("TotalInterval:" + totalInterval
                    + ", Can not divide into intervals. So calculating cumulative.");
            return getStatsByTimeWithCumulative(null, timestampStart, timestampEnd);
        }

        for (int i = 1; i <= totalInterval; i++) {
            logger.debug("" + i + ". interval...");
            timestampEnd = timestampStart + eachIntervalInMs;

            if (!service.equalsIgnoreCase(Constants.SERVICE_ALL)) {//search,login,favorites
                List<Edr> edrList = edrRepository
                        .findBySpecificTimeByService(service,
                                new Timestamp(timestampStart),
                                new Timestamp(timestampEnd));
                statFormList.addAll(convertIntoOneCumulativeStatForm(service,
                        edrList,
                        timestampStart,
                        timestampEnd));
            } else {//ALL
                statFormList
                        .addAll(getStatsByTimeWithCumulative(null, timestampStart, timestampEnd));
            }
            timestampStart = timestampEnd;
        }
        return statFormList;
    }

    public List<StatisticForm> getStatsByTimeWithCumulative(String service, long timestampStart,
                                                            long timestampEnd) {
        logger.debug("EdrService.getStatsByTimeWithCumulative.");

        List<StatisticForm> kpiFormList = new ArrayList<StatisticForm>();

        if (service == null) {
            logger.debug("Getting edrs for all services.");
            List<Edr> edrList = edrRepository.findBySpecificTime(new Timestamp(timestampStart),
                    new Timestamp(timestampEnd));
            logger.debug("edrList Size:" + edrList.size());
            kpiFormList = convertIntoOneCumulativeStatForm(null,
                    edrList,
                    timestampStart,
                    timestampEnd);
        } else {

            logger.debug("Getting edrs for service:" + service);

            List<Edr> edrList = edrRepository
                    .findBySpecificTimeByService(service,
                            new Timestamp(timestampStart),
                            new Timestamp(timestampEnd));
            logger.debug("edrList Size:" + edrList.size());
            kpiFormList = convertIntoOneCumulativeStatForm(service,
                    edrList,
                    timestampStart,
                    timestampEnd);
        }
        logger.debug("KPI List size: " + kpiFormList.size());
        return kpiFormList;
    }

    public List<KpiForm> calculateKpiByTimeWithInterval(String service, long timestampStart,
                                                        long timestampEnd, String interval) {


        logger.debug("EdrService.getStatsByTimeWithInterval.");

        List<KpiForm> kpiFormList = new ArrayList<KpiForm>();
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
            return calculateKpiByTimeWithCumulative(null, timestampStart, timestampEnd);
        }

        logger.debug("TimeDifference:" + timeDifference);
        logger.debug("TotalInterval:" + totalInterval);

        if (totalInterval == 0) {
            logger.debug("TotalInterval:" + totalInterval
                    + ", Can not divide into intervals. So calculating cumulative.");
            return calculateKpiByTimeWithCumulative(null, timestampStart, timestampEnd);
        }

        for (int i = 1; i <= totalInterval; i++) {
            logger.debug(i + ". interval...");
            timestampEnd = timestampStart + eachIntervalInMs;

            if (!service.equalsIgnoreCase(Constants.SERVICE_ALL)) {//search,login,favorites
                List<Edr> edrList = edrRepository
                        .findBySpecificTimeByService(service,
                                new Timestamp(timestampStart),
                                new Timestamp(timestampEnd));
                kpiFormList.addAll(convertIntoOneCumulativeKpiForm(service,
                        edrList,
                        timestampStart,
                        timestampEnd));
            } else {//ALL
                kpiFormList
                        .addAll(calculateKpiByTimeWithCumulative(null, timestampStart, timestampEnd));
            }
            timestampStart = timestampEnd;
        }
        return kpiFormList;
    }

    public List<KpiForm> calculateKpiByTimeWithCumulative(String service, long timestampStart,
                                                          long timestampEnd) {
        logger.debug("EdrService.calculateKpiByTimeWithCumulative.");
        List<KpiForm> kpiFormList = null;
        if (service == null) {
            logger.debug("Getting KPIs for all services.");
            List<Edr> edrList = edrRepository.findBySpecificTime(new Timestamp(timestampStart),
                    new Timestamp(timestampEnd));
            logger.debug("edrList Size:" + edrList.size());
            kpiFormList = convertIntoOneCumulativeKpiForm(service,
                    edrList,
                    timestampStart,
                    timestampEnd);

        } else {
            logger.debug("Getting KPIs for service:" + service);
            List<Edr> edrList = edrRepository
                    .findBySpecificTimeByService(service,
                            new Timestamp(timestampStart),
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
    public List<StatisticForm> convertIntoOneCumulativeStatForm(String service, List<Edr> edrList,
                                                                long startTime, long endTime) {
        logger.debug("EdrService.convertIntoOneCumulativeStatForm.");

        List<StatisticForm> statFormList = new ArrayList<StatisticForm>();
        int allErrorCount = 0, searchErrorCount = 0, loginErrorCount = 0, favoriteErrorCount = 0,
                searchRequestCount = 0, loginRequestCount = 0, favoriteRequestCount = 0;

        Date startDate = new Date(new Timestamp(startTime).getTime());
        Date endDate = new Date(new Timestamp(endTime).getTime());

        for (Edr edr : edrList) {
            logger.debug(edr.getServiceName());
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

            statFormList.add(allStatForm);
            statFormList.add(searchStatForm);
            statFormList.add(loginStatForm);
            statFormList.add(favStatForm);

            return statFormList;
        } else {
            StatisticForm statForm = new StatisticForm();
            statForm.setServiceName(service);
            statForm.setNumApiCalls(edrList.size());
            statForm.setError(allErrorCount);
            statForm.setStartTime(startDate);
            statForm.setEndTime(endDate);
            statFormList.add(statForm);
            return statFormList;
        }
    }

    private List<KpiForm> convertIntoOneCumulativeKpiForm(String service, List<Edr> edrList,
                                                         long startTime, long endTime) {
        logger.debug("EdrService.convertIntoOneCumulativeKpiForm.");

        List<KpiForm> kpiFormList = new ArrayList<KpiForm>();
        int searchRequestCount = 0, loginRequestCount = 0, favoriteRequestCount = 0;
        double searchProcessingTime = 0, loginProcessingTime = 0, favoriteProcessingTime = 0,
                totalProcessingTime = 0;
        double avgSearchResponseTime = 0, avgLoginResponseTime = 0, avgFavoriteResponseTime = 0,
                avgResponseTime = 0;
        long minSearchResponseTime = Long.MAX_VALUE, minLoginResponseTime = Long.MAX_VALUE,
                minFavoriteResponseTime = Long.MAX_VALUE, minResponseTime = Long.MAX_VALUE;
        long maxSearchResponseTime = Long.MIN_VALUE, maxLoginResponseTime = Long.MIN_VALUE,
                maxFavoriteResponseTime = Long.MIN_VALUE, maxResponseTime = Long.MIN_VALUE;

        Date startDate = new Date(new Timestamp(startTime).getTime());
        Date endDate = new Date(new Timestamp(endTime).getTime());

        for (Edr edr : edrList) {
            if (edr.getServiceName().equalsIgnoreCase(Constants.SERVICE_SEARCH)) {
                searchRequestCount++;
                searchProcessingTime += edr.getProcessingTimeInMiliseconds();
                if (minSearchResponseTime > edr.getProcessingTimeInMiliseconds()) {
                    minSearchResponseTime = edr.getProcessingTimeInMiliseconds();
                }
                if (maxSearchResponseTime < edr.getProcessingTimeInMiliseconds()) {
                    maxSearchResponseTime = edr.getProcessingTimeInMiliseconds();
                }
            } else if (edr.getServiceName().equalsIgnoreCase(Constants.SERVICE_LOGIN)) {
                loginRequestCount++;
                loginProcessingTime += edr.getProcessingTimeInMiliseconds();
                if (minLoginResponseTime > edr.getProcessingTimeInMiliseconds()) {
                    minLoginResponseTime = edr.getProcessingTimeInMiliseconds();
                }
                if (maxLoginResponseTime < edr.getProcessingTimeInMiliseconds()) {
                    maxLoginResponseTime = edr.getProcessingTimeInMiliseconds();
                }
            } else if (edr.getServiceName().equalsIgnoreCase(Constants.SERVICE_FAVORITES)) {
                favoriteRequestCount++;
                favoriteProcessingTime += edr.getProcessingTimeInMiliseconds();
                if (minFavoriteResponseTime > edr.getProcessingTimeInMiliseconds()) {
                    minFavoriteResponseTime = edr.getProcessingTimeInMiliseconds();
                }
                if (maxFavoriteResponseTime < edr.getProcessingTimeInMiliseconds()) {
                    maxFavoriteResponseTime = edr.getProcessingTimeInMiliseconds();
                }
            }
            if (minResponseTime > edr.getProcessingTimeInMiliseconds()) {
                minResponseTime = edr.getProcessingTimeInMiliseconds();
            }
            if (maxResponseTime < edr.getProcessingTimeInMiliseconds()) {
                maxResponseTime = edr.getProcessingTimeInMiliseconds();
            }
            totalProcessingTime += edr.getProcessingTimeInMiliseconds();
        }

        avgSearchResponseTime = (double) (searchProcessingTime / searchRequestCount);
        avgLoginResponseTime = (double) (loginProcessingTime / loginRequestCount);
        avgFavoriteResponseTime = (double) (favoriteProcessingTime / favoriteRequestCount);
        avgResponseTime = (double) (totalProcessingTime / edrList.size());

        logger.debug("avgResponseTime:" + avgResponseTime);
        logger.debug("totalProcessingTime:" + totalProcessingTime);
        logger.debug("total requests:" + edrList.size());
        logger.debug("avgFavoriteResponseTime:" + avgFavoriteResponseTime);
        logger.debug("favoriteProcessingTime:" + favoriteProcessingTime);
        logger.debug("favoriteRequestCount:" + favoriteRequestCount);

        if (service == null) {
            // ALL
            KpiForm kpiAllForm = setKpiForm("All", avgResponseTime,
                    maxResponseTime, minResponseTime, startDate, endDate);

            KpiForm searchKpiForm = setKpiForm("search", avgSearchResponseTime,
                    maxSearchResponseTime, minSearchResponseTime, startDate, endDate);

            KpiForm loginKpiForm = setKpiForm("login", avgLoginResponseTime,
                    maxLoginResponseTime, minLoginResponseTime, startDate, endDate);

            KpiForm favKpiForm = setKpiForm("favorites", avgFavoriteResponseTime,
                    maxFavoriteResponseTime, minFavoriteResponseTime, startDate, endDate);

            kpiFormList.add(kpiAllForm);
            kpiFormList.add(searchKpiForm);
            kpiFormList.add(loginKpiForm);
            kpiFormList.add(favKpiForm);

        } else {
            //TODO:
            //Depend on the service name, set the KpiForm
        }
        return kpiFormList;
    }

    private KpiForm setKpiForm(String service, double avgResponseTime, long maxResponseTime, long minResponseTime,
                       Date startDate, Date endDate) {
        KpiForm kpiForm = new KpiForm();
        kpiForm.setServiceName(service);
        kpiForm.setAvgRespTime(avgResponseTime);
        kpiForm.setMaxRespTime(maxResponseTime);
        kpiForm.setMinRespTime(minResponseTime);
        kpiForm.setStartTime(startDate);
        kpiForm.setEndTime(endDate);
        return kpiForm;
    }

    public List<StatisticForm> getStatsByTimeWithCumulativeV2(String service, long timestampStart,
                                                            long timestampEnd) {
        logger.debug("EdrService.getStatsByTimeWithCumulative.");

        List<StatisticForm> statFormList = new ArrayList<StatisticForm>();

        if (service == null) {
            logger.debug("Getting edrs for all services.");
            statFormList.add(getStatFormByServiceNameAndTimeRange(null, timestampStart, timestampEnd));
//            statFormList.add(getStatFormByServiceNameAndTimeRange(Constants.SERVICE_FAVORITES, timestampStart, timestampEnd));
//            statFormList.add(getStatFormByServiceNameAndTimeRange(Constants.SERVICE_LOGIN, timestampStart, timestampEnd));
//            statFormList.add(getStatFormByServiceNameAndTimeRange(Constants.SERVICE_SEARCH, timestampStart, timestampEnd));
        } else {
            logger.debug("Getting edrs for service:" + service);
            statFormList.add(getStatFormByServiceNameAndTimeRange(service, timestampStart, timestampEnd));
        }
        logger.debug("KPI List size: " + statFormList.size());
        return statFormList;
    }

    public List<StatisticForm> getStatsByTimeWithIntervalV2(String service, long timestampStart,
                                                          long timestampEnd, String interval) {

        logger.debug("EdrService.getStatsByTimeWithInterval.");

        List<StatisticForm> statFormList = new ArrayList<StatisticForm>();
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

        logger.debug("TimeDifference:" + timeDifference);
        logger.debug("TotalInterval:" + totalInterval);

        if (totalInterval == 0) {
            logger.debug("TotalInterval:" + totalInterval
                    + ", Can not divide into intervals. So calculating cumulative.");
            return getStatsByTimeWithCumulativeV2(service, timestampStart, timestampEnd);
        }

        for (int i = 1; i <= totalInterval; i++) {
            logger.debug("" + i + ". interval...");
            timestampEnd = timestampStart + eachIntervalInMs;

            if (!service.equalsIgnoreCase(Constants.SERVICE_ALL)) {//search,login,favorites
                statFormList.add(getStatFormByServiceNameAndTimeRange(service,
                        timestampStart,
                        timestampEnd));
            } else {//ALL
                statFormList
                        .addAll(getStatsByTimeWithCumulativeV2(null, timestampStart, timestampEnd));
            }
            timestampStart = timestampEnd;
        }
        return statFormList;
    }

    public List<KpiForm> getKpiByTimeWithCumulativeV2(String service, long timestampStart,
                                                      long timestampEnd) {
        logger.debug("EdrService.calculateKpiByTimeWithCumulative.");
        List<KpiForm> kpiFormList = new ArrayList<KpiForm>();
        if (service == null) {
            logger.debug("Getting KPIs for all services.");

            kpiFormList.add(getKpiFormByServiceNameAndTimeRange(null, timestampStart, timestampEnd));
//            kpiFormList.add(getKpiFormByServiceNameAndTimeRange(Constants.SERVICE_FAVORITES, timestampStart, timestampEnd));
//            kpiFormList.add(getKpiFormByServiceNameAndTimeRange(Constants.SERVICE_LOGIN, timestampStart, timestampEnd));
//            kpiFormList.add(getKpiFormByServiceNameAndTimeRange(Constants.SERVICE_SEARCH, timestampStart, timestampEnd));

        } else {
            logger.debug("Getting KPIs for service:" + service);
            kpiFormList.add(getKpiFormByServiceNameAndTimeRange(service, timestampStart, timestampEnd));

        }

        logger.debug("KPI List size: " + kpiFormList.size());
        return kpiFormList;

    }

    public List<KpiForm> getKpiByTimeWithIntervalV2(String service, long timestampStart,
                                                    long timestampEnd, String interval) {


        logger.debug("EdrService.getStatsByTimeWithInterval.");

        List<KpiForm> kpiFormList = new ArrayList<KpiForm>();
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
            return getKpiByTimeWithCumulativeV2(null, timestampStart, timestampEnd);
        }

        logger.debug("TimeDifference:" + timeDifference);
        logger.debug("TotalInterval:" + totalInterval);

        if (totalInterval == 0) {
            logger.debug("TotalInterval:" + totalInterval
                    + ", Can not divide into intervals. So calculating cumulative.");
            return getKpiByTimeWithCumulativeV2(service, timestampStart, timestampEnd);
        }

        for (int i = 1; i <= totalInterval; i++) {
            logger.debug(i + ". interval...");
            timestampEnd = timestampStart + eachIntervalInMs;

            if (!service.equalsIgnoreCase(Constants.SERVICE_ALL)) {//search,login,favorites
                kpiFormList.add(getKpiFormByServiceNameAndTimeRange(service,
                        timestampStart,
                        timestampEnd));
            } else {//ALL
                kpiFormList
                        .addAll(getKpiByTimeWithCumulativeV2(null, timestampStart, timestampEnd));
            }
            timestampStart = timestampEnd;
        }
        return kpiFormList;
    }

    private StatisticForm getStatFormByServiceNameAndTimeRange(String serviceName, long timestampStart, long timestampEnd){
        Date startDate = new Date(new Timestamp(timestampStart).getTime());
        Date endDate = new Date(new Timestamp(timestampEnd).getTime());
        long success = 0, failed = 0;
        if(serviceName==null){
            List<CountForm> countForms = edrRepository.getCountApi(new Timestamp(timestampStart),
                    new Timestamp(timestampEnd));
            for(int i=0;i<countForms.size();i++){
                if(countForms.get(i).isSuccess()){
                    success = countForms.get(i).getCount();
                } else {
                    failed = countForms.get(i).getCount();
                }
            }
            return new StatisticForm(startDate, endDate, "All", success + failed, failed);
        } else {
            List<CountForm> countForms = edrRepository.getCountApiByServiceName(serviceName,
                    new Timestamp(timestampStart), new Timestamp(timestampEnd));
            for(int i=0;i<countForms.size();i++){
                if(countForms.get(i).isSuccess()){
                    success = countForms.get(i).getCount();
                } else {
                    failed = countForms.get(i).getCount();
                }
            }
            return new StatisticForm(startDate, endDate, serviceName, success + failed, failed);
        }
    }

    private KpiForm getKpiFormByServiceNameAndTimeRange(String serviceName, long timestampStart, long timestampEnd){
        Date startDate = new Date(new Timestamp(timestampStart).getTime());
        Date endDate = new Date(new Timestamp(timestampEnd).getTime());
        KpiForm result = null;

        if(serviceName==null){
            Statistic statistic = edrRepository.findKpiByTimestamp(new Timestamp(timestampStart), new Timestamp(timestampEnd));
            result = new KpiForm(startDate, endDate, "All",
                    statistic.getAvg(), statistic.getMin(), statistic.getMax());
        } else {
            Statistic statistic = edrRepository.findKpiByTimestampAndService(serviceName, new Timestamp(timestampStart), new Timestamp(timestampEnd));
            result = new KpiForm(startDate, endDate, serviceName,
                    statistic.getAvg(), statistic.getMin(), statistic.getMax());
        }
        return result;
    }
}
