package patientenportal.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity(name="USERGROUP")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@PrimaryKeyJoinColumn(name="baseclass_id")
public class UserGroup extends BaseClass {

	@XmlTransient
	@OneToOne
	private User user;
	
	@ManyToMany(mappedBy="users")
	private Set<Permission> permissons;
	
	@Override
	public boolean equals(Object obj) {
		return true; //(obj == this) || (obj instanceof UserRole) && Id != null && Id.equals(((UserRole) obj).getId());
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@XmlTransient
	public Set<Permission> getPermissions() {
		return this.permissons;
	}
	
	public void setPermissions(Set<Permission> permissions) {
		this.permissons = permissions;
	}
	
	
	public Role getRole(){
		return Role.Other;
	}

}
