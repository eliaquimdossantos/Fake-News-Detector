package br.ufrn.imd.project.domain.controller;

import br.ufrn.imd.project.domain.model.DataSetModel;

public class DataSetController extends DataSetModel {
	
	private String fileName;
	private int totalFakeNews; // Quantidade de fakenews na base de dados
		
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public DataSetController() {		
		super();			
	}
	
	public void startDataSet(String fileName) {
		this.fileName = fileName;
		super.loadDataSet(this.fileName);
		totalFakeNews = super.numberOfNews(); 		
	}
	
	public FakeNews getFakeNews(int i) {						
		FakeNews f = super.readFakeNews(i);		
		
		return f;
	}
	
	public int getNumberOfNews() {		
		return totalFakeNews; 
	}
}
