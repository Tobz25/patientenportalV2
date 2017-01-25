package patientenportal.service;

import java.util.List;

import patientenportal.dao.UserDAOImpl;
import patientenportal.dao.VitalDateDAOImpl;
import patientenportal.helper.DataNotFoundException;
import patientenportal.model.VitalDate;

public class VitalDataService {
	
	public List<VitalDate> getVitalData(){
		VitalDateDAOImpl vdi = new VitalDateDAOImpl();
		List<VitalDate> VitalData = vdi.getAll();
		if(VitalData.size()>0){
			return VitalData;
		}
		throw new DataNotFoundException("No Vital Data found");
	}
	
	
	public VitalDate getVitalDataById(long VitalDataId) {
		VitalDateDAOImpl vdi = new VitalDateDAOImpl();
		List<VitalDate> VitalData = vdi.getAll();
		for (VitalDate pf : VitalData) {
			if (pf.getId() == VitalDataId){
				return pf;
			}
		}
		throw new DataNotFoundException("No Vital Data found for id " + VitalDataId);
	}

	/*
	public VitalDate addVitalDate(VitalDate vitalDate) {
		VitalDateDAOImpl vdi = new VitalDateDAOImpl();
		vdi.addEntity(vitalDate);
		return null;
	}*/
}
