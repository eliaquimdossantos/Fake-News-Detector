package br.ufrn.imd.project.domain.controller;

import java.util.HashSet;

public class MainController {

	private HashSet<String> usedAlgorithms;
	private DataSetController fakeNewsDataBase;
	WebScraping webNews;
	private double similarutyPercentage;
	SimilaritySystemController newSimilarity;

	public MainController(String newsLink, String dataSetFileName) {
		usedAlgorithms = new HashSet<String>();		
		webNews = new WebScraping(newsLink);

		fakeNewsDataBase = new DataSetController();
		fakeNewsDataBase.startDataSet(dataSetFileName);
	}

	public void configAlgorithm(String algorithmName) {
		usedAlgorithms.add(algorithmName);
	}

	public double calculateSimilarutyPercentage() {
		int numberOfNews = fakeNewsDataBase.getNumberOfNews();

		for (int i = 0; i < numberOfNews; i++) {
			newSimilarity = new SimilaritySystemController(fakeNewsDataBase.getFakeNews(i + 1), webNews.getWebNews());
			
			for (String algorithm : usedAlgorithms) {
				newSimilarity.addAlgorithm(algorithm);
			}
			
			if (newSimilarity.getSimilarutyValue() > similarutyPercentage) {
				similarutyPercentage = newSimilarity.getSimilarutyValue();
			}

			if (similarutyPercentage == 1.0) {
				break;
			}
		}

		return similarutyPercentage;

	}

}