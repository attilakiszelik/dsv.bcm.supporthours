package com.kaev.supporthours.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kaev.supporthours.model.LogEntry;
import com.kaev.supporthours.model.ReportRow;
import com.kaev.supporthours.repository.LogEntryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final LogEntryRepository logEntryRepository;
	
	public List<ReportRow> createReport(String year){
		
		List<LogEntry> logEntries = logEntryRepository.findAllInYear(year);

		List<ReportRow> reportRows = new ArrayList<ReportRow>();;
		
		logEntries.forEach(l -> {
			
			//miden sor esetében a szükséges változók rögzítése
			String project = l.getProject();
			int month = Integer.parseInt(l.getTimestamp().substring(4,6));
			int index = month-1;
			int workminutes = l.getWorkminutes();
			
			Optional<ReportRow> existingReportRow = reportRows.stream().filter(r -> r.getProject().equals(project)).findFirst();
			
			if (existingReportRow.isPresent()) {
				
				ReportRow r = existingReportRow.get();
				
				//munkaórák számának növelée
				r.setUsages(index, r.getUsages(index) + workminutes); 
				
			} else {
				
				//ha a projektnek nincs még riport sora
				ReportRow newReportRow = initializeNewRepotRow();
				newReportRow.setProject(project);
				newReportRow.setUsages(index, workminutes);
				reportRows.add(newReportRow);
				
			}
			
		});
		
		return reportRows;
		
	}

	public ReportRow initializeNewRepotRow() {
		
		List<Integer> usages = new ArrayList<Integer>();
			for (int i=0; i<=11; i++)
				usages.add(0);

		return new ReportRow("",usages);
		
	}

}
