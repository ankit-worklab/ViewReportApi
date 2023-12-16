package com.Insurance.service;

import java.io.IOException;
import java.util.List;

import com.Insurance.request.SearchRequest;
import com.Insurance.response.SearchResponse;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	
public List<String> getUniquePlanName();
	
	public List<String> getUniquePlanStatus();
	
	public List<SearchResponse> search(SearchRequest criteria);
	
	public void generatePdf(HttpServletResponse response)throws DocumentException, IOException;
	
	public void generateExcel(HttpServletResponse response) throws IOException  ;
	
}
