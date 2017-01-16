package patientenportal.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@XmlRootElement
@Entity
public class PatientFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long Id;

	@OneToOne
	private Patient patient;
	
	@OneToMany(mappedBy="patientFile", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<CaseFile> caseFiles;
	
	@OneToMany(mappedBy="patientFile", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<MedicalDocument> medicalDocuments;
	
	@Override
	public boolean equals(Object obj) {
		return (obj == this) || (obj instanceof PatientFile) && Id != null && Id.equals(((PatientFile) obj).getId());
	}

	public long getId() {
		return Id;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Set<CaseFile> getCaseFiles() {
		return this.caseFiles;
	}
	
	public void setCaseFiles(Set<CaseFile> caseFiles) {
		this.caseFiles = caseFiles;
	}
	
	public void addCaseFile(CaseFile caseFile) {
		caseFile.setPatientCase(this);
		this.caseFiles.add(caseFile);
	}
	
	public Set<MedicalDocument> getMedicalDocuments() {
		return this.medicalDocuments;
	}
	
	public void setMedicalDocuments(Set<MedicalDocument> documents) {
		this.medicalDocuments = documents;
	}
	
	public void addMedicalDocument(MedicalDocument medicalDocument) {
		medicalDocument.setPatientFile(this);
		this.medicalDocuments.add(medicalDocument);
	}
	
}