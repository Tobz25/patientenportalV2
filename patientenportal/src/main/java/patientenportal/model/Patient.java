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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany
	@JoinTable
	private Set<Doctor> linkedDoctors;
	
	@ManyToMany
	@JoinTable
	private Set<MedicalStaff> linkedMedicalStaff;
	
	@ManyToMany
	@JoinTable
	private Set<Relative> linkedRelatives;
	
	@XmlTransient
	public PatientFile getPatientFile() {
		return this.patientFile;
	}
	
	public void setPatientFile(PatientFile patientFile) {
		this.patientFile = patientFile;
	}
	
	
	/*
	 * Permissions
	 */
	
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
	
	/*
	 * linkedDoctors
	 */
	
	public Set<Doctor> getLinkedDoctors() {
		return this.linkedDoctors;
	}
	
	public void setLinkedDoctors(Set<Doctor> linkedDoctors) {
		this.linkedDoctors = linkedDoctors;
	}
	
	public void addLinkedDoctor(Doctor doc) {
		this.linkedDoctors.add(doc);
		doc.addLinkedToPatient(this);
	}
	
	public void removeLinkedDoctor(Doctor doc) {
		this.linkedDoctors.remove(doc);
		doc.removeLinkedToPatient(this);
	}
	
	/*
	 * linkedMedicalStaff
	 */
	
	public Set<MedicalStaff> getLinkedMedicalStaff() {
		return this.linkedMedicalStaff;
	}
	
	public void setLinkedMedicalStaff(Set<MedicalStaff> linkedMedicalStaff) {
		this.linkedMedicalStaff = linkedMedicalStaff;
	}
	
	public void addLinkedMedicalStaff(MedicalStaff med) {
		this.linkedMedicalStaff.add(med);
		med.addLinkedToPatient(this);
	}
	
	public void removeLinkedMedicalStaff(MedicalStaff med) {
		this.linkedMedicalStaff.remove(med);
		med.removeLinkedToPatient(this);
	}
	
	/*
	 * linkedRelatives
	 */
	
	public Set<Relative> getLinkedRelatives() {
		return this.linkedRelatives;
	}
	
	public void setLinkedRelatives(Set<Relative> linkedRelatives) {
		this.linkedRelatives = linkedRelatives;
	}
	
	public void addLinkedRelative(Relative relative) {
		this.linkedRelatives.add(relative);
		relative.addLinkedToPatient(this);
	}
	
	public void removeLinkedRelative(Relative relative) {
		this.linkedRelatives.remove(relative);
		relative.removeLinkedToPatient(this);
	}
	/*
	 * Override
	 */

	@Override
	public Role getRole() {
		return Role.Patient;
	}
}