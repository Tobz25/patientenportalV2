package patientenportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class Patient extends UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long Id;

	@OneToOne
	private User user;

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