package patientenportal.model;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javax.persistence.Id;
import javax.persistence.OneToMany;

@XmlRootElement
@Entity
@AttributeOverrides({  
    @AttributeOverride(name="baseclass_id", column=@Column(name="baseclass_id")),  
    @AttributeOverride(name="user", column=@Column(name="user"))  
})  
public class Patient extends UserGroup {
	
	@OneToOne(mappedBy = "patient")
	private PatientFile patientFile;
	
	@OneToMany(mappedBy="authorisedByPatient", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<Permission> permissionsAuthorised;
	
	@XmlTransient
	public PatientFile getPatientFile() {
		return this.patientFile;
	}
	
	public void setPatientFile(PatientFile patientFile) {
		this.patientFile = patientFile;
	}
	
	
	@XmlTransient
	public Set<Permission> getPermissions() {
		return this.permissionsAuthorised;
	}
	
	public void setPermissions(Set<Permission> permissions) {
		this.permissionsAuthorised = permissions;
	}
	
	public void addPermission(Permission permission) {
		this.permissionsAuthorised.add(permission);
		permission.setPatient(this);
	}
	
	public void deletePermission(Permission permission) {
		this.permissionsAuthorised.remove(permission);
		permission.setPatient(null);
	}
}