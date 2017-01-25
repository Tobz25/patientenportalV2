package patientenportal.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@XmlRootElement
@Entity(name="medicationintake")
@PrimaryKeyJoinColumn(name="baseclass_id")
public class MedicationIntake extends BaseClass{
	
	@ManyToOne
	private MedicationPrescription referencedPrescription;

	@ManyToOne
	private Treatment treatment;
	
	@Column
	private String description;
	
	@Column
	private String drug;
	
	@Column(name="datetime", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	@Override
	public boolean equals(Object obj) {
		return true; //(obj == this) || (obj instanceof MedicationIntake) && Id != null && Id.equals(((MedicationIntake) obj).getId());
	}

	public MedicationPrescription getPrescription() {
		return this.referencedPrescription;
	}

	public void setPrescription(MedicationPrescription prescription) {
		this.referencedPrescription = prescription;
	}
	
	public Treatment getTreatment() {
		return this.treatment;
	}
	
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDrug() {
		return this.drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date datetime) {
		this.dateTime = datetime;
	}
	
}