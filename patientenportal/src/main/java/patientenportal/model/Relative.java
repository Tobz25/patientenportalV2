package patientenportal.model;


import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@AttributeOverrides({  
    @AttributeOverride(name="baseclass_id", column=@Column(name="baseclass_id")),  
    @AttributeOverride(name="user", column=@Column(name="user"))  
})  
public class Relative extends UserGroup {

	@ManyToMany(mappedBy="linkedRelatives")
	private Set<Patient> linkedToPatient;

	/*
	 * Getter & Setter
	 * 
	 */
	@XmlTransient
	public Set<Patient> getLinkedToPatients() {
		return this.linkedToPatient;
	}
	
	public void setLinkedToPatients(Set<Patient> patients) {
		this.linkedToPatient = patients;
	}
	
	public void addLinkedToPatient(Patient patient) {
		this.linkedToPatient.add(patient);
	}
	
	public void removeLinkedToPatient(Patient patient) {
		this.linkedToPatient.remove(patient);
	}
	
	@Override
	public Role getRole() {
		return Role.Relative;
	}
}
