package com.Insurance.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="ELIGIBILITY_DTLS")
@Data
public class EligibilityDtls {
	@Id
	@GeneratedValue()
	@Column(name="ELIG_ID")
	private int eligibilityId;
	private String name;
	private String email;
	private Long mobNo;
	private Character gender;
	@Column(name="SSN_NO")
	private String ssnNo;
	@Column(name="PLAN_NM")
	private String planName;
	@Column(name="PLAN_STATUS")
	private String planStatus;
	@Column(name="START_DT")
	private LocalDate startDate;
	@Column(name="END_DT")
	private LocalDate endDate;
	@Column(name="CREATED_DT")
	private LocalDate createdDate;
	@Column(name="UPDATED_DT")
	private LocalDate updatedDate;
	@Column(name="CREADTED_BY")
	private String createdBy;
	@Column(name="UPDATED_BY")
	private String updateBy;
}
