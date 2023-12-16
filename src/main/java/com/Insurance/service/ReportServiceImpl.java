package com.Insurance.service;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Insurance.entities.EligibilityDtls;
import com.Insurance.repo.EligibilityDtlsRepo;
import com.Insurance.request.SearchRequest;
import com.Insurance.response.SearchResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private EligibilityDtlsRepo dtlsRepo;
	
	
	@Override
	public List<String> getUniquePlanName() {
		return dtlsRepo.getPlanName();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		
		return dtlsRepo.getPlanStatus();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		
		List<SearchResponse> response = new ArrayList<>();
		EligibilityDtls queryBuilder=new EligibilityDtls();
		String planName= request.getPlanName();
		if(planName != null && planName!="") {
			queryBuilder.setPlanName(planName);
		}
		String planStatus = request.getPlanStatus();
		if(planStatus != null && planStatus!="") {
			queryBuilder.setPlanStatus(planStatus);
		}
		LocalDate startDate = request.getStartDate();
		if(startDate != null ) {
			queryBuilder.setStartDate(startDate);
		}
		LocalDate endDate = request.getEndDate();
		if(endDate != null ) {
			queryBuilder.setEndDate(endDate);
		}
		
		Example<EligibilityDtls> example = Example.of(queryBuilder);
			
		List<EligibilityDtls> entities = dtlsRepo.findAll(example);
			
//		entities.forEach(entity->{
//			 SearchResponse rsp = new SearchResponse();
//			 
////			 rsp.setName(entity.getName());
////			 rsp.setGender(entity.getGender());
////			 rsp.setEmail(entity.getEmail());
////			 rsp.setMobNo(entity.getMobNo());
////			 rsp.setSsnNo(entity.getSsnNo());
//			 
//			 BeanUtils.copyProperties(entity, rsp);
//			 response.add(rsp);
//			 
//		});
		
		for(EligibilityDtls entity : entities) {
			SearchResponse rsp = new SearchResponse();
			 BeanUtils.copyProperties(entity, rsp);
			 response.add(rsp);
		}
			
			return response;
			
			
		}

	

	@Override
	public void generateExcel(HttpServletResponse response) throws IOException {
		
		List<EligibilityDtls> entities =   dtlsRepo.findAll();
		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("S No.");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Email");
		headerRow.createCell(4).setCellValue("Mobile No");
		headerRow.createCell(5).setCellValue("SSN NO");
		int rowNo=1;
		
		for(EligibilityDtls entity:entities){
			
			HSSFRow dataRow = sheet.createRow(rowNo);
			dataRow.createCell(0).setCellValue(rowNo);
			dataRow.createCell(1).setCellValue(entity.getName());
			dataRow.createCell(2).setCellValue(entity.getGender());
			dataRow.createCell(3).setCellValue(entity.getEmail());
			dataRow.createCell(4).setCellValue(entity.getMobNo());
			dataRow.createCell(5).setCellValue(entity.getSsnNo());
			
			rowNo++;
			
		}
		
		ServletOutputStream outputStream =response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
	}
	@Override
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		 PdfWriter.getInstance(document, response.getOutputStream());
		
		 document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("Search Report", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	
	        PdfPTable table = new PdfPTable(5);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
	        table.setSpacingBefore(10);
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	}
	
	 private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("Name", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Gender", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Mobile No", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Email", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("SSN NO", font));
	        table.addCell(cell);       
	    }
	     
	    private void writeTableData(PdfPTable table) {
	    	List<EligibilityDtls> entities = dtlsRepo.findAll();
	        for (EligibilityDtls entity : entities) {
	            table.addCell(entity.getName());
	            table.addCell(String.valueOf(entity.getGender()));
	            table.addCell(String.valueOf(entity.getMobNo()));
	            table.addCell(entity.getEmail());
	            table.addCell(entity.getSsnNo());
	            
	       
	        }
	    }
	
	

}
