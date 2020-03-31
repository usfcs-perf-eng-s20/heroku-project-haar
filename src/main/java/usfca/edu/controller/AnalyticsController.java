package usfca.edu.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import usfca.edu.json.model.ConfigForm;
import usfca.edu.json.model.EdrForm;
import usfca.edu.json.model.KpiForm;
import usfca.edu.json.model.StatisticForm;
import usfca.edu.persistence.EdrRepository;
import usfca.edu.service.logic.EdrService;

@RestController
public class AnalyticsController {

    private final EdrRepository edrRepository;
    private final EdrService    edrService;

    public AnalyticsController(EdrRepository edrRepository, EdrService edrService) {
        this.edrRepository = edrRepository;
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
        List<KpiForm> kpiFormList;
        System.out.println("Start Time:" + new Date(new Timestamp(startTime).getTime()));
        System.out.println("End Time:" + new Date(new Timestamp(endTime).getTime()));

        if (interval == null || interval.equalsIgnoreCase("") || service == null
                || service.equalsIgnoreCase("")) {
            System.out.println("GetKpis API called without interval or service.");
            kpiFormList = edrService.calculateKpiByTimeWithCumulativeV2(service, startTime, endTime);
        } else {
            System.out.println("GetKpis API called with interval:" + interval + " and service :"
                    + service);
            kpiFormList = edrService
                    .calculateKpiByTimeWithInterval(service, startTime, endTime, interval);
        }

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
        List<StatisticForm> statisticFormList = new ArrayList<StatisticForm>();

        System.out.println("Start Time:" + new Date(new Timestamp(startTime).getTime()));
        System.out.println("End Time:" + new Date(new Timestamp(endTime).getTime()));

        if (interval == null || interval.equalsIgnoreCase("") || service == null
                || service.equalsIgnoreCase("")) {
            System.out.println("GetStats API called without interval or service.");
            statisticFormList = edrService
                    .getStatsByTimeWithCumulativeV2(service, startTime, endTime);
        } else {
            System.out.println("GetStats API called with interval:" + interval + " and service :"
                    + service);

            statisticFormList = edrService
                    .getStatsByTimeWithIntervalV2(service, startTime, endTime, interval);
        }
        return statisticFormList;
    }

    @ApiOperation(value = "This API will save EDR to database!", response = String.class)
    @PostMapping("/saveEdr")
    @ResponseStatus(value = HttpStatus.OK)
    String saveEdr(@RequestBody EdrForm edrForm) {

        System.out.println("test.saveEdr....");

        if (edrService.saveEdr(edrForm)) {
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
            System.out.println("Analytics is ON!");
        } else {
            System.out.println("Analytics is OFF!");
        }

        /**
         * TODO: @Razan 
         * Create a responseObject ("confirm": true,
                                    "message": "Config updated successfully");
                                    return this object!!
         */
        return "Config updated successfully";
    }
}
