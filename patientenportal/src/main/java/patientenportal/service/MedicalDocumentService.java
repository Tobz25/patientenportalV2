package patientenportal.service;

import java.util.List;

import patientenportal.dao.MedicalDocumentDAOImpl;
import patientenportal.dao.VitalDateDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.MedicalDocument;
import patientenportal.model.VitalDate;

public class MedicalDocumentService {
	
	public List<MedicalDocument> getMedicalDocument(){
		MedicalDocumentDAOImpl mdi = new MedicalDocumentDAOImpl();
		List<MedicalDocument> MedicalDocument = mdi.getAll();
		if(MedicalDocument.size()>0){
			return MedicalDocument;
		}
		throw new DataNotFoundException("No Medical Documents found");
	}

	
	public MedicalDocument getMedicalDocumentById(long MedicalDocumentId) {
		MedicalDocumentDAOImpl mdi = new MedicalDocumentDAOImpl();
		List<MedicalDocument> MedicalDocument = mdi.getAll();
		for (MedicalDocument md : MedicalDocument) {
			if (md.getId() == MedicalDocumentId){
				return md;
			}
		}
		throw new DataNotFoundException("No Medical Document found for id " + MedicalDocumentId);
	}
	
	public MedicalDocument addMedicalDocument(MedicalDocument MedicalDocument){
		MedicalDocumentDAOImpl mdi = new MedicalDocumentDAOImpl();
		MedicalDocument newMedicalDocument = mdi.addEntityAndReturn(MedicalDocument);
		return newMedicalDocument;
	}
	
	public MedicalDocument deleteMedicalDocumentById(long MedicalDocumentId) {
		MedicalDocumentDAOImpl mdi = new MedicalDocumentDAOImpl();
		List<MedicalDocument> allMedicalDocument = mdi.getAll();
		for (MedicalDocument md : allMedicalDocument){
			if (md.getId() == MedicalDocumentId){
				mdi.deleteEntity(md);	
			}
		}
		throw new DataNotFoundException("No Medical Document found for id " + MedicalDocumentId);
	}
}