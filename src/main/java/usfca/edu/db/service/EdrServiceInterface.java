package usfca.edu.db.service;

import usfca.edu.db.model.Edr;
import usfca.edu.json.model.EdrForm;

import java.util.List;

public interface EdrServiceInterface {

    public boolean saveEdr(EdrForm edrForm);

    public List<Edr> getAllEdr();

    public List<Edr> getEdrsByServiceName(String serviceName);

    public List<Edr> getEdrByTime(long timestamp);
}
