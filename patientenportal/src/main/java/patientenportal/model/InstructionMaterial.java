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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@XmlRootElement
@Entity
/*Schulungsunterlagen --> kranheitsfallspezifisch*/
public class InstructionMaterial{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long Id;

	@ManyToMany
	@JoinTable
	private Set<CaseFile> caseFiles;

	@Column
	private String description;
	
	@Override
	public boolean equals(Object obj) {
		return (obj == this) || (obj instanceof InstructionMaterial) && Id != null && Id.equals(((InstructionMaterial) obj).getId());
	}

	/*Id*/
	
	public long getId() {
		return Id;
	}
	
	/*Casefiles*/

	public Set<CaseFile> getCaseFiles() {
		return this.caseFiles;
	}

	public void setCaseFiles(Set<CaseFile> caseFiles) {
		this.caseFiles = caseFiles;
	}
	
	public void addCaseFile(CaseFile caseFile) {
		caseFile.getInstructionMaterials().add(this);
		this.caseFiles.add(caseFile);
	}
	
	/*Description*/
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}