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
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@XmlRootElement
@Entity(name="medication")
@PrimaryKeyJoinColumn(name="baseclass_id")
public class Medication extends BaseClass{
	
	@ManyToMany(mappedBy="medications")
	private Set<MedicationPrescription> prescriptions;

	@Column
	private String description;
	
	@Column
	private String drug;
	
	
	@Override
	public boolean equals(Object obj) {
		return true; //(obj == this) || (obj instanceof Medication) && Id != null && Id.equals(((Medication) obj).getId());
	}

	@XmlTransient
	public Set<MedicationPrescription> getPrescriptions() {
		return this.prescriptions;
	}

	public void setPrescriptions(Set<MedicationPrescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	
	public void addPrescription(MedicationPrescription prescription) {
		this.prescriptions.add(prescription);
		prescription.getMedications().add(this);
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
}