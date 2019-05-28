package br.ufrn.imd.lpii.domain;

import java.util.Date;

public class NewsFromWeb extends News {
	
	private String link;

	public NewsFromWeb(Date date, String link) {
		super(date);
		this.link = link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String getLink() {
		return link;
	}
}
