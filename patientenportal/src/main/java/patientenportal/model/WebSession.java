package patientenportal.model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity(name="websession")
@PrimaryKeyJoinColumn(name="baseclass_id")
public class WebSession extends BaseClass {

	@Column
	private String token;
	
	@OneToOne
	private User user;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public WebSession() {
		
	}

	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
