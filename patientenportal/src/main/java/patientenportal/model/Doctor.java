package patientenportal.model;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@AttributeOverrides({  
    @AttributeOverride(name="baseclass_id", column=@Column(name="baseclass_id")),  
    @AttributeOverride(name="user", column=@Column(name="user"))  
})  
public class Doctor extends UserGroup {
	

}
