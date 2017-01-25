package patientenportal.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.glassfish.jersey.server.ResourceConfig;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@XmlRootElement
@Entity(name="medicaldocument")
public class MedicalDocument extends BaseClass{
	
	@ManyToOne
	private CaseFile caseFile;

	@ManyToOne
	private PatientFile patientFile;

	@Column
	private String description;
	
	@Column(name="startdatetime", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date added;
	
	@ManyToOne
	private Doctor doctorAdded;
	
	@Override
	public boolean equals(Object obj) {
		return true; // (obj == this) || (obj instanceof MedicalDocument) && Id != null && Id.equals(((MedicalDocument) obj).getId());
	}

	public CaseFile getCaseFile() {
		return this.caseFile;
	}

	public void setCaseFile(CaseFile caseFile) {
		this.caseFile = caseFile;
	}
	
	public PatientFile getPatientFile() {
		return this.patientFile;
	}

	public void setPatientFile(PatientFile patientFile) {
		this.patientFile = patientFile;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateTimeofAdded() {
		return this.added;
	}

	public void setDateTimeOfAdded(Date datetime) {
		this.added = datetime;
	}
	
	public Doctor getDoctorAdded() {
		return this.doctorAdded;
	}
	
	public void setDoctorAdded(Doctor doctor) {
		this.doctorAdded = doctor;
	}
	
}