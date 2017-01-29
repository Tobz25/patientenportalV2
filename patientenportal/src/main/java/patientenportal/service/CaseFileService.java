package patientenportal.service;

/*
 * Service-Implementierung /Anwendungslogik, welche alle Methoden, die Fallaten betreffen, enthält
 * 
 */
import java.util.List;
import java.util.Set;

import patientenportal.dao.CaseFileDAOImpl;
import patientenportal.dao.PatientFileDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.CaseFile;
import patientenportal.model.PatientFile;

public class CaseFileService {
	
	public Set<CaseFile> getCaseFiles(long patientFileId){
		PatientFileDAOImpl pfdi = new PatientFileDAOImpl();
		List<PatientFile> patientFiles = pfdi.getAll();
		for (PatientFile pf : patientFiles) {
			if (pf.getId() == patientFileId){
				return pf.getCaseFiles();
			}
		}
		throw new DataNotFoundException("No Case Files found for patient with id "+ patientFileId);
	}

	
	public CaseFile getCaseFileById(long caseFileId) {
		CaseFileDAOImpl cfdi = new CaseFileDAOImpl();
		List<CaseFile> CaseFiles = cfdi.getAll();
		for (CaseFile pf : CaseFiles) {
			if (pf.getId() == caseFileId){
				return pf;
			}
		}
		throw new DataNotFoundException("No Case file found for id " + caseFileId);
	}
	
	public CaseFile createCaseFile(CaseFile caseFile){
		CaseFileDAOImpl cfdi = new CaseFileDAOImpl();
		CaseFile createdCase = cfdi.addEntityAndReturn(caseFile);
		return createdCase;
	}
}
