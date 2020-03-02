package usfca.edu.db.service;

import java.util.List;

import usfca.edu.db.model.Edr;
import usfca.edu.json.model.EdrForm;
import usfca.edu.json.model.KpiForm;

public interface EdrServiceInterface {

    public boolean saveEdr(EdrForm edrForm);

    public List<Edr> getEdrByTimeWithInterval(String service, long timestampStart,
                                              long timestampEnd, String interval);

    public List<KpiForm> calculateKpiByTimeWithCumulative(String service, long timestampStart,
                                                          long timestampEnd);

    public List<KpiForm> calculateKpiByTimeWithInterval(String service, long timestampStart,
                                                        long timestampEnd, String interval);

}
