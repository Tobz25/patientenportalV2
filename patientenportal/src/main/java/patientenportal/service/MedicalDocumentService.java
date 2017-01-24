package patientenportal.service;

import java.util.List;

import patientenportal.dao.MedicalDocumentDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.MedicalDocument;

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
}