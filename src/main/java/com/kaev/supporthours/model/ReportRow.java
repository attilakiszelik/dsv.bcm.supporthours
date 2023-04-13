package com.kaev.supporthours.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportRow {

	private String project;
	private List<Integer> usages;
	
	public int getUsages(int index) {
		return this.usages.get(index);
	}

	public void setUsages(int index, int workminutes) {
		this.usages.set(index, workminutes);
	}
	
}