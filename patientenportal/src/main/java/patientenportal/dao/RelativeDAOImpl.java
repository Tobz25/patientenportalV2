package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.Patient;
import patientenportal.model.Relative;
import patientenportal.model.User;

public class RelativeDAOImpl extends GenericDAOImpl<Relative, Long>{

	@Override
	public Relative addEntityIntern(Relative entity) {
		Relative newRelative = new Relative();
        
        newRelative.setUser(entity.getUser());
        newRelative.setLinkedToPatients(entity.getLinkedToPatients());
        return newRelative; 
	}

}

