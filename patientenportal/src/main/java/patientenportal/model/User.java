package patientenportal.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
public class User extends BaseClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long Id;

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
	private Set<UserRole> userRoles;
	
	@OneToOne
	private UserRole activeRole;
	
	public String toString() {
		return "(" + Id + " "+ username +" " + salutation + " " + firstname + " " + 
				lastname + "" + emailaddress +" " + password + ") ";
	}

	@Override
	public boolean equals(Object obj) {
		return (obj == this) || (obj instanceof User) && Id != null && Id.equals(((User) obj).getId());
	}

	

	/* ::::::::::::::::::::::::::::::::::::::
	 * Methods - Hibernate - userRoles
	 * ::::::::::::::::::::::::::::::::::::::
	 */

	/*Id*/
	
	public long getId() {
		return Id;
	}

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
	
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}
	
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	public void addUserRole(UserRole userRole) {
		userRole.setUser(this);
		this.userRoles.add(userRole);
	}
	
	/*activeRole*/
	
	public UserRole getActiveUserRole() {
		return this.activeRole;
	}
	
	public void setActiveUserRole(UserRole activeRole) {
		this.activeRole = activeRole;
	}

	public String getFullName() {
		return lastname + ", " + firstname;
	}
}

