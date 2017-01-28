package patientenportal.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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

import org.glassfish.jersey.server.ResourceConfig;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@XmlRootElement
@Entity(name="treatment")
@PrimaryKeyJoinColumn(name="baseclass_id")
public class Treatment extends BaseClass{
	
	@ManyToOne
	private CaseFile caseFile;

	@Column
	private String description;
	
	@OneToMany(mappedBy="treatment", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<MedicationPrescription> prescriptions;
	
	@OneToMany(mappedBy="treatment", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<MedicationIntake> medicationIntakes;
	
	@Column(name="startdatetime", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	
	@Column
	private boolean finished;
	
	@ManyToOne
	private Doctor responsibleDoctor;
	
	@Override
	public boolean equals(Object obj) {
		return true; //(obj == this) || (obj instanceof Treatment) && Id != null && Id.equals(((Treatment) obj).getId());
	}

	public CaseFile getCaseFile() {
		return this.caseFile;
	}

	public void setCaseFile(CaseFile caseFile) {
		this.caseFile = caseFile;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlTransient
	public Set<MedicationPrescription> getPrescriptions() {
		return this.prescriptions;
	}
	
	public void setPrescriptions(Set<MedicationPrescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	
	public void addPrescription(MedicationPrescription prescription) {
		prescription.setTreatment(this);
		this.prescriptions.add(prescription);
	}
	
	@XmlTransient
	public Set<MedicationIntake> getMedicationIntakes() {
		return this.medicationIntakes;
	}
	
	public void setMedicationIntakes(Set<MedicationIntake> intakes) {
		this.medicationIntakes = intakes;
	}
	
	public void addMedicationIntake(MedicationIntake intake) {
		intake.setTreatment(this);
		this.medicationIntakes.add(intake);
	}
	
	public Date getStartDateTime() {
		return this.start;
	}

	public void setStartDateTime(Date datetime) {
		this.start = datetime;
	}
	
	public boolean getFinished() {
		return this.finished;
	}
	
	public void setFinished(boolean finished){
		this.finished = finished;
	}
	
	public Doctor getResponsibleDoctor() {
		return responsibleDoctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.responsibleDoctor = doctor;
	}


}