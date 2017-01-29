package patientenportal.model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
/*
 * Eine WebSession wird erzeugt, wenn sich ein User mit gültigem username und password anmeldet. Sie enthält die User, einen Zeitstempel
 * und einen Token, der an den User zurückgegeben wird.
 * 
 */

@XmlRootElement
@Entity(name="websession")
@PrimaryKeyJoinColumn(name="baseclass_id")
public class WebSession extends BaseClass {

	@Column
	private String token;
	
	@OneToOne
	private User user;
	
	@Column(name="validtill", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date validtill;
	
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
	
	public Date getValidTill() {
		return validtill;
	}
	
	public void setValidTill(Date validtill) {
		this.validtill = validtill;
	}
}
