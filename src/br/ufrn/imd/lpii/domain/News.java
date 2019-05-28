package br.ufrn.imd.lpii.domain;

import java.util.Date;
import java.util.ArrayList;

public class News implements NewsInterface {	
	private Date date;
	private ArrayList<String> paragraphs;
	
	public News(Date date) {
		this.date = date;
	}
	
	public void addParagraph(String paragraph) {
		paragraphs.add(paragraph);
	}

	@Override
	public String acquireNews() {
		String fullNews = "";
		
		for (String p : paragraphs) {
			fullNews += p;
		}
		
		return fullNews;
	}

	@Override
	public String acquireParagraph(int number) {
		// TODO lembrar de criar exceção caso número seja maior que a quantidade
		// de parágrafos
		return paragraphs.get(number-1);		
	}

	@Override
	public int acquireTotalParagraphs() {		
		return paragraphs.size();
	}
	
	
}


