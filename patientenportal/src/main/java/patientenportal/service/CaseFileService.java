package patientenportal.service;

import java.util.List;

import patientenportal.dao.CaseFileDAOImpl;
import patientenportal.dao.PatientDAOImpl;
import patientenportal.dao.PatientFileDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.CaseFile;
import patientenportal.model.Patient;
import patientenportal.model.PatientFile;

public class CaseFileService {
	
	public List<CaseFile> getCaseFiles(){
		CaseFileDAOImpl pdi = new CaseFileDAOImpl();
		List<CaseFile> CaseFiles = pdi.getAll();
		if(CaseFiles.size()>0){
			return CaseFiles;
		}
		throw new DataNotFoundException("No Case Files found");
	}

	/*
	public CaseFile getCaseFileById(long caseFileId) {
		CaseFileDAOImpl cfdi = new CaseFileDAOImpl();
		List<CaseFile> CaseFiles = cfdi.getAll();
		for (CaseFile pf : CaseFiles) {
			if (pf.getId() == caseFileId){
				return pf;
			}
		}
		throw new DataNotFoundException("No Case file found for id " + caseFileId);
	}*/
}
