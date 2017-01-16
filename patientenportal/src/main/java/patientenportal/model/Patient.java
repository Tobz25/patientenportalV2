package patientenportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CascadeType;

import javax.persistence.Id;

@XmlRootElement
@Entity
public class Patient extends UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long Id;

	@OneToOne
	private User user;
	
	@OneToOne(mappedBy = "patient")
	private PatientFile patientCase;
	
	@Override
	public boolean equals(Object obj) {
		return (obj == this) || (obj instanceof Patient) && Id != null && Id.equals(((Patient) obj).getId());
	}

	public long getId() {
		return Id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}