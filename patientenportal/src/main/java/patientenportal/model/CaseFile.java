package patientenportal.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@XmlRootElement
@Entity
public class CaseFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long Id;

	@ManyToOne
	private PatientFile patientFile;

	@OneToMany(mappedBy="caseFile", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<VitalDate> vitalData;
	
	@OneToMany(mappedBy="caseFile", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<Treatment> treatments;
	
	@OneToMany(mappedBy="caseFile", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Set<MedicalDocument> medicalDocuments;
	
	@ManyToMany(mappedBy="caseFiles")
	private Set<InstructionMaterial> instructionMaterials;
	
	@Column
	private String diagnose;
	
	@Override
	public boolean equals(Object obj) {
		return (obj == this) || (obj instanceof CaseFile) && Id != null && Id.equals(((CaseFile) obj).getId());
	}

	public long getId() {
		return Id;
	}

	/*Patientfile*/
	
	public PatientFile getPatientCase() {
		return this.patientFile;
	}

	public void setPatientCase(PatientFile patientCase) {
		this.patientFile = patientCase;
	}
	
	/*VitalData*/
	@XmlTransient
	public Set<VitalDate> getVitalData() {
		return this.vitalData;
	}
	
	public void setVitalData(Set<VitalDate> vitalData) {
		this.vitalData = vitalData;
	}
	
	public void addVitalDate(VitalDate vitalDate) {
		vitalDate.setCaseFile(this);
		this.vitalData.add(vitalDate);
	}
	
	/*Treatment*/
	@XmlTransient
	public Set<Treatment> getTreatments() {
		return this.treatments;
	}
	
	public void setTreatments(Set<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	public void addTreatment(Treatment treatment) {
		treatment.setCaseFile(this);
		this.treatments.add(treatment);
	}
	
	/*MedicalDocuments*/
	
	@XmlTransient
	public Set<MedicalDocument> getMedicalDocuments() {
		return this.medicalDocuments;
	}
	
	public void setMedicalDocuments(Set<MedicalDocument> documents) {
		this.medicalDocuments = documents;
	}
	
	public void addMedicalDocument(MedicalDocument medicalDocument) {
		medicalDocument.setCaseFile(this);
		this.medicalDocuments.add(medicalDocument);
	}
	
	/*InstructionMaterial*/
	
	public Set<InstructionMaterial> getInstructionMaterials() {
		return this.instructionMaterials;
	}
	
	public void setInstructionMaterial(Set<InstructionMaterial> instructionMaterials) {
		this.instructionMaterials = instructionMaterials;
	}
	
	public void addInstructionMaterial(InstructionMaterial instructionMaterial) {
		instructionMaterial.getCaseFiles().add(this);
		this.instructionMaterials.add(instructionMaterial);
	}
	
	/*Diagnose*/
	
	public String getDiagnose() {
		return this.diagnose;
	}
	
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

}