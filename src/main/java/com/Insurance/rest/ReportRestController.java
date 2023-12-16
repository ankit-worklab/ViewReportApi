package com.Insurance.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Insurance.request.SearchRequest;
import com.Insurance.response.SearchResponse;
import com.Insurance.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;
@RestController
public class ReportRestController {
	@Autowired
	private ReportService service;
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlanNames(){
		List<String> planNames= service.getUniquePlanName();
	return 	new ResponseEntity<>(planNames,HttpStatus.OK);
		
	}
	@GetMapping("/statuses")
	public ResponseEntity<List<String>> getPlanStatuses(){
		List<String> planStatues = service.getUniquePlanStatus();
		return 	new ResponseEntity<>(planStatues,HttpStatus.OK);
	}
	
	@PostMapping("/search")		
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request){
		List<SearchResponse> entities = service.search(request);
		return new ResponseEntity<>(entities,HttpStatus.OK);
	}
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-Stream");
		String headerKey = "Content-Disposition";
		String headerValue="attachment;fileName=data.xls";
		response.setHeader(headerKey, headerValue);
		service.generateExcel(response);
	}
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response) throws IOException{
		response.setContentType("application/Pdf");
		String headerKey = "Content-Disposition";
		String headerValue="attachment;fileName=data.pdf";
		response.setHeader(headerKey, headerValue);
		service.generatePdf(response);
	}
}
	
	

