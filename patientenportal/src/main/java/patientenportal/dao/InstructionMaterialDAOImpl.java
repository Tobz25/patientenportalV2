package patientenportal.dao;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import patientenportal.model.InstructionMaterial;
import patientenportal.model.MedicalDocument;
import patientenportal.model.Medication;
import patientenportal.model.Treatment;
import patientenportal.model.User;
import patientenportal.model.VitalDate;

public class InstructionMaterialDAOImpl extends GenericDAOImpl<InstructionMaterial, Long>{

	@Override
	public InstructionMaterial addEntityIntern(InstructionMaterial entity) {
		InstructionMaterial newIM = new InstructionMaterial();
        
		newIM.setCaseFiles(entity.getCaseFiles());
		newIM.setDescription(entity.getDescription());
		return newIM; 
	}

}

