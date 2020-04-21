package usfca.edu.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.splunk.logging.SplunkCimLogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import usfca.edu.json.model.*;
import usfca.edu.persistence.EdrRepository;
import usfca.edu.service.logic.EdrService;

@CrossOrigin(maxAge = 3600)
@RestController
public class AnalyticsController {
    private final Logger logger = LoggerFactory.getLogger(AnalyticsController.class);

    private final EdrService    edrService;

    public AnalyticsController(EdrService edrService) {
        this.edrService = edrService;
    }

    @ApiOperation(value = "getKpis API will return KPI values!(Min/Avg/Max Response Times) ",
            response = String.class)
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Returns list of KpiForm!"),
                     @ApiResponse(code = 401,
                             message = "You are not authorized to view the resource"),
                     @ApiResponse(code = 403,
                             message = "Accessing the resource you were trying to reach is forbidden"),
                     @ApiResponse(code = 404,
                             message = "The resource you were trying to reach is not found")

            })
    @GetMapping("/getKpis")
    List<KpiForm> getKpis(@RequestParam(required = false) String service,
                          @RequestParam("startTime") long startTime,
                          @RequestParam("endTime") long endTime,
                          @RequestParam(required = false) String interval) {
        long startTimer = System.currentTimeMillis();
        List<KpiForm> kpiFormList;

        if (interval == null || interval.equalsIgnoreCase("") || service == null
                || service.equalsIgnoreCase("")) {
            kpiFormList = edrService.getKpiByTimeWithCumulativeV2(service, startTime, endTime);
        } else {
            kpiFormList = edrService
                    .getKpiByTimeWithIntervalV2(service, startTime, endTime, interval);
        }


        long endTimer = System.currentTimeMillis();
        String message = String.format("GetKpis from %s to %s",
                new Date(new Timestamp(startTime).getTime()).toString(),
                new Date(new Timestamp(endTime).getTime()).toString());
        LogForm logForm = new LogForm("Analytics", endTimer - startTimer,
                false,  message, "getKpis");
        logger.info(logForm.toString());
        return kpiFormList;
    }

    @ApiOperation(
            value = "getStats API will return numbers of API calls/Error Counts interval by interval for a given "
                    + "service within the given time! If interval parameter is empty, API will return cumulative "
                    + "results for given service. ",
            response = String.class)
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Returns Statistic Form"),
                     @ApiResponse(code = 401,
                             message = "You are not authorized to view the resource"),
                     @ApiResponse(code = 403,
                             message = "Accessing the resource you were trying to reach is forbidden"),
                     @ApiResponse(code = 404,
                             message = "The resource you were trying to reach is not found")

            })
    @GetMapping("/getStats")
    List<StatisticForm> getStats(@RequestParam(required = false) String service,
                                 @RequestParam("startTime") long startTime,
                                 @RequestParam("endTime") long endTime,
                                 @RequestParam(required = false) String interval) {

        long startTimer = System.currentTimeMillis();
        List<StatisticForm> statisticFormList = new ArrayList<StatisticForm>();

        if (interval == null || interval.equalsIgnoreCase("") || service == null
                || service.equalsIgnoreCase("")) {
            statisticFormList = edrService
                    .getStatsByTimeWithCumulativeV2(service, startTime, endTime);
        } else {
            statisticFormList = edrService
                    .getStatsByTimeWithIntervalV2(service, startTime, endTime, interval);
        }

        long endTimer = System.currentTimeMillis();
        String message = String.format("GetStats from %s to %s",
                new Date(new Timestamp(startTime).getTime()).toString(),
                new Date(new Timestamp(endTime).getTime()).toString());
        LogForm logForm = new LogForm("Analytics", endTimer - startTimer,
                false,  message, "getStats");
        logger.info(logForm.toString());
        return statisticFormList;
    }

    @ApiOperation(value = "This API will save EDR to database!", response = String.class)
    @PostMapping("/saveEdr")
    @ResponseStatus(value = HttpStatus.OK)
    String saveEdr(@RequestBody EdrForm edrForm) {

        long startTimer = System.currentTimeMillis();

        boolean result = edrService.saveEdr(edrForm);

        long endTimer = System.currentTimeMillis();
        String message = String.format("/saveEdr called by %s", edrForm.getServiceName());
        LogForm logForm = new LogForm("Analytics", endTimer - startTimer,
                !result,  message, "saveEdr", edrForm);
        logger.info(logForm.toString());
        if(result){
            return "Edr saved successfully!!!!!!!!!!!!";
        } else {
            return "Edr could not be saved!!!!!!!!!!!!";
        }
    }

    @GetMapping("/")
    String index() {
        return "Analytics Service is running";
    }

    @ApiOperation(value = "This API will configure Analytics!", response = String.class)
    @PostMapping("/config")
    @ResponseStatus(value = HttpStatus.OK)
    String config(@RequestBody ConfigForm configForm) {
        /**
         *
         * TODO:  To be defined in future! We don't have any endpoints speaking with....
         */
        if (configForm.isAnalytics()) {
            logger.debug("Analytics is ON!");
        } else {
            logger.debug("Analytics is OFF!");
        }

        /**
         * TODO: @Razan
         * Create a responseObject ("confirm": true,
                                    "message": "Config updated successfully");
                                    return this object!!
         */
        return "Config updated successfully";
    }

    // commenting this out for now
    // @GetMapping("/loaderio-194394ee21a8f08c9ff74696dc772029")
    // String loaderIo() {
    //     return "loaderio-194394ee21a8f08c9ff74696dc772029";
    // }

    @GetMapping("/loaderio-40c19b8228b02c384e998c96cc829ff3")
    String loaderIo() {
        return "loaderio-40c19b8228b02c384e998c96cc829ff3";
    }
}
