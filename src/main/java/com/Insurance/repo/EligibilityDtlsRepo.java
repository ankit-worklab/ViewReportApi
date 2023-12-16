package com.Insurance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Insurance.entities.EligibilityDtls;

public interface EligibilityDtlsRepo extends JpaRepository<EligibilityDtls,Integer> {
	
	@Query("select distinct(e.planName) from EligibilityDtls e")
	public List<String> getPlanName();
	
	@Query("select distinct(e.planStatus) from EligibilityDtls e")
	public List<String> getPlanStatus();
}
