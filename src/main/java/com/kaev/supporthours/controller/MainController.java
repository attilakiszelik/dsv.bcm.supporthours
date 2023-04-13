package com.kaev.supporthours.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaev.supporthours.model.ReportRow;
import com.kaev.supporthours.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/supporthours")
@RequiredArgsConstructor
public class MainController {

	private final ReportService reportService;
	
	@GetMapping
	public List<ReportRow> getMapping(){
		return reportService.createReport("2023");
	}
	
}