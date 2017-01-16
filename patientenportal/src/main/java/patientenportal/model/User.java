package patientenportal.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



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

	public long getId() {
		return Id;
	}

	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailaddress() {
		return this.emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getSalutation() {
		return this.salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFullName() {
		return lastname + ", " + firstname;
	}
}

