package patientenportal.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
<<<<<<< HEAD
import org.hibernate.annotations.FetchMode;
=======
>>>>>>> 5e38aabae8e87022f9e79243ce0ced1278eb6366

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Entity(name="BASECLASS")
@Inheritance(strategy=InheritanceType.JOINED)
public class BaseClass {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	@Fetch(FetchMode.JOIN)
	@ManyToMany(mappedBy="elements")
	private Set<Permission> permissons;
	
	/*
	 * Getter & Setter
	 */
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@XmlTransient
	public Set<Permission> getPermissions() {
		return this.permissons;
	}
	
	public void setPermissions(Set<Permission> permissions) {
		this.permissons = permissions;
	}
}

