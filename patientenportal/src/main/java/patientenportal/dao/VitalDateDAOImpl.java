package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.User;
import patientenportal.model.VitalDate;

public class VitalDateDAOImpl extends GenericDAOImpl<VitalDate, Long>{

	@Override
	public VitalDate addEntityIntern(VitalDate entity) {
		VitalDate newVitalDate = new VitalDate();
        
        newVitalDate.setCaseFile(entity.getCaseFile());
        newVitalDate.setCategory(entity.getCategory());
        newVitalDate.setValue(entity.getValue());
        newVitalDate.setDatetime(entity.getDatetime());
        return newVitalDate; 
	}

}

