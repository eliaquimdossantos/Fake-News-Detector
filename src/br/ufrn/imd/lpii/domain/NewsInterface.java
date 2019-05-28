package br.ufrn.imd.lpii.domain;

public interface NewsInterface {
	public void addParagraph(String paragraph);
	public String acquireNews();
	public String acquireParagraph(int number);
	public int acquireTotalParagraphs();
}