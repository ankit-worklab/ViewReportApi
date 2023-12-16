package com.Insurance.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.Insurance.entities.EligibilityDtls;
import com.Insurance.repo.EligibilityDtlsRepo;

public class AppRunner implements ApplicationRunner {
	@Autowired
	EligibilityDtlsRepo repo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
	
		EligibilityDtls entity = new EligibilityDtls();
		entity.setEligibilityId(1);
		entity.setName("John");
		entity.setGender('M');
		entity.setEmail("john@gmail.com");
		entity.setMobNo(887455154l);
		entity.setSsnNo("U52874545");
		entity.setPlanName("SNAP");
		entity.setPlanStatus("Approved");
		repo.save(entity);
		
		EligibilityDtls entity1 = new EligibilityDtls();
		entity1.setEligibilityId(2);
		entity1.setName("Martin");
		entity1.setGender('M');
		entity1.setEmail("martin@gmail.com");
		entity1.setMobNo(888978154l);
		entity1.setSsnNo("U528778945");
		entity1.setPlanName("CCAP");
		entity1.setPlanStatus("Denied");
		repo.save(entity);
	
	
	EligibilityDtls entity2 = new EligibilityDtls();
	entity2.setEligibilityId(3);
	entity2.setName("Smith");
	entity2.setGender('M');
	entity2.setEmail("smith@gmail.com");
	entity2.setMobNo(987565323l);
	entity2.setSsnNo("U987478945");
	entity2.setPlanName("MediCap");
	entity2.setPlanStatus("InProgress");
	repo.save(entity);
	}
	
}
