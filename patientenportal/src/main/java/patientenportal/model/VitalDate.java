package patientenportal.model;

import java.util.Date;

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
import javax.persistence.ManyToOne;

@XmlRootElement
@Entity(name="vitaldate")
@PrimaryKeyJoinColumn(name="baseclass_id")
public class VitalDate extends BaseClass{
	
	@ManyToOne
	private CaseFile caseFile;

	@Column
	private String value;
	
	@Column 
	private String category;
	
	@Column(name="datetime", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datetime;
	
	@Override
	public boolean equals(Object obj) {
		return true; //(obj == this) || (obj instanceof VitalDate) && Id != null && Id.equals(((VitalDate) obj).getId());
	}

	public CaseFile getCaseFile() {
		return this.caseFile;
	}

	public void setCaseFile(CaseFile CaseFile) {
		this.caseFile = caseFile;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}


}