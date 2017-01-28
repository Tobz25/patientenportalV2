package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.Administrator;
import patientenportal.model.Patient;
import patientenportal.model.Relative;
import patientenportal.model.User;

public class AdministratorDAOImpl extends GenericDAOImpl<Administrator, Long>{

	@Override
	public Administrator addEntityIntern(Administrator entity) {
		Administrator newAdministrator = new Administrator();
        
		newAdministrator.setUser(entity.getUser());
        return newAdministrator; 
	}

}

