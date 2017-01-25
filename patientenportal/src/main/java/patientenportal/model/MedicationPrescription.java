package patientenportal.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@XmlRootElement
@Entity
public class MedicationPrescription{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long Id;

	@ManyToOne
	private Treatment treatment;

	@ManyToMany
	@JoinTable
	private Set<Medication> medications;
	
	@Column
	private String description;
	
	@Column(name="startdatetime", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date writingDateTime;
	
	@ManyToOne
	private Doctor responsibleDoctor;
	
	@Override
	public boolean equals(Object obj) {
		return (obj == this) || (obj instanceof MedicationPrescription) && Id != null && Id.equals(((MedicationPrescription) obj).getId());
	}

	public long getId() {
		return Id;
	}

	public Treatment getTreatment() {
		return this.treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
	@XmlTransient
	public Set<Medication> getMedications() {
		return this.medications;
	}
	
	public void setMedication(Set<Medication> medications) {
		this.medications = medications;
	}
	
	public void addMedication(Medication medication) {
		medication.getPrescriptions().add(this);
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getWritingDateTime() {
		return this.writingDateTime;
	}

	public void setWritingDateTime(Date datetime) {
		this.writingDateTime = datetime;
	}
	
	public Doctor getResponsibleDoctor() {
		return responsibleDoctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.responsibleDoctor = doctor;
	}

}