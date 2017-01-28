package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.BaseClass;
import patientenportal.model.CaseFile;
import patientenportal.model.Doctor;
import patientenportal.model.Patient;
import patientenportal.model.Relative;
import patientenportal.model.User;

public class BaseClassDAOImpl extends GenericDAOImpl<BaseClass, Long>{

	@Override
	public BaseClass addEntityIntern(BaseClass entity) {
		BaseClass newCaseFile = new BaseClass();
        
        return newCaseFile; 
	}

}

