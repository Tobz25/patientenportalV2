package patientenportal.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.CascadeType;

import javax.persistence.Id;

@XmlRootElement
@Entity
@AttributeOverrides({  
    @AttributeOverride(name="id", column=@Column(name="id")),  
    @AttributeOverride(name="user", column=@Column(name="user"))  
})  
public class Patient extends UserRole {
	

	@OneToOne(mappedBy = "patient")
	private PatientFile patientFile;
	
	@XmlTransient
	public PatientFile getPatientFile() {
		return this.patientFile;
	}
	
	public void setPatientFile(PatientFile patientFile) {
		this.patientFile = patientFile;
	}
	
	
}