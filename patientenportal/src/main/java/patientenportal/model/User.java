package patientenportal.model;

import java.security.Principal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
@Entity(name="User")
@PrimaryKeyJoinColumn(name="baseclass_id")
public class User extends BaseClass implements Principal{
	
	@Column(nullable = false)
	private String username;

	@Column
	private String salutation;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(length = 80)
	private String emailaddress;

	@Column
	private String password;
	
	@OneToMany
	private Set<UserGroup> userRoles;
	
	@OneToOne
	private UserGroup activeRole;
	
	public String toString() {
		return "(" + getId() + " "+ username +" " + salutation + " " + firstname + " " + 
				lastname + "" + emailaddress +" " + password + ") ";
	}

	@Override
	public boolean equals(Object obj) {
		return true;//return (obj == this) || (obj instanceof User) && Id != null && Id.equals(((User) obj).getId());
	}

	

	/* ::::::::::::::::::::::::::::::::::::::
	 * Methods - Hibernate - userRoles
	 * ::::::::::::::::::::::::::::::::::::::
	 */

	/*Username*/
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*Password*/
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	/*Firstname*/
	
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/*Lastname*/
	
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/*Emailaddress*/

	public String getEmailaddress() {
		return this.emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	/*Salutation*/
	
	public String getSalutation() {
		return this.salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	
	/*Useroles*/
	
	@XmlTransient
	public Set<UserGroup> getUserRoles() {
		return this.userRoles;
	}
	
	public void setUserRoles(Set<UserGroup> userRoles) {
		this.userRoles = userRoles;
	}
	
	public void addUserRole(UserGroup userRole) {
		userRole.setUser(this);
		this.userRoles.add(userRole);
	}
	
	/*activeRole*/
	
	@XmlTransient
	public UserGroup getActiveUserRole() {
		return this.activeRole;
	}
	
	public void setActiveUserRole(UserGroup activeRole) {
		this.activeRole = activeRole;
	}

	public String getFullName() {
		return lastname + ", " + firstname;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}

