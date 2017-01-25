package patientenportal.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;;


@XmlRootElement
@Entity(name="permission")
@PrimaryKeyJoinColumn(name="baseclass_id")
public class Permission extends BaseClass {
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PERMISSION_ELEMENTS", 
	       joinColumns = { @JoinColumn(name = "permission_id") }, 
	       inverseJoinColumns = { @JoinColumn(name = "element_id") })
	private Set<BaseClass> elements;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PERMISSION_USER", 
	       joinColumns = { @JoinColumn(name = "permission_id") }, 
	       inverseJoinColumns = { @JoinColumn(name = "usergroup_id") })
	private Set<UserGroup> usergroups;
	
	@ManyToOne
	private Patient authorisedByPatient;

	@Enumerated(EnumType.STRING)
	private PermissionType permissionType;

	@Override
	public boolean equals(Object obj) {
		return true; //(obj == this) || (obj instanceof Permission) && getId() == (((Permission) obj).getId());
	}

	/*authorisedByPatient*/
	
	@XmlTransient
	public Patient getPatient() {
		return this.authorisedByPatient;
	}

	public void setPatient(Patient patient) {
		this.authorisedByPatient = patient;
	}
	
	public PermissionType getPermissionType() {
		return this.permissionType;
	}
	
	public void setPermissionType(PermissionType permissionType) {
		this.permissionType = permissionType;
	}
	
	public Set<UserGroup> getUserGroup() {
		return this.usergroups;
	}
	
	public void setUserGroups(Set<UserGroup> usergroups) {
		this.usergroups = usergroups;
	}
	
	public void addUserGroup(UserGroup usergroup) {
		this.usergroups.add(usergroup);
		usergroup.getPermissions().add(this);
	}
	
	public void deleteUserGroup(UserGroup usergroup) {
		this.usergroups.remove(usergroup);
		usergroup.getPermissions().remove(this);
	}
	
	public Set<BaseClass> getElements() {
		return this.elements;
	}
	
	public void setElements(Set<BaseClass> elements) {
		this.elements = elements;
	}
	
	public void addElements(BaseClass baseClass) {
		this.elements.add(baseClass);
		baseClass.getPermissions().add(this);
	}
	
	public void deleteElement(BaseClass baseClass) {
		this.elements.remove(baseClass);
		baseClass.getPermissions().remove(baseClass);
	}
	

}