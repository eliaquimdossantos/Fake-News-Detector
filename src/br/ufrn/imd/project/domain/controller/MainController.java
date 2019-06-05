package br.ufrn.imd.project.domain.controller;

import java.util.ArrayList;
import java.util.HashSet;

import br.ufrn.imd.project.domain.controller.exception.DataSetNotFoundException;
import br.ufrn.imd.project.domain.model.DataSetNoContentException;

public class MainController {

	private HashSet<String> usedAlgorithms;
	private ArrayList<String> toGUIWarnings;
	private DataSetController fakeNewsDataBase;
	WebScraping webNews;
	private double similarutyPercentage;
	SimilaritySystemController newSimilarity;

	public MainController(String newsLink, String dataSetFileName) {
		toGUIWarnings = new ArrayList<String>();
		usedAlgorithms = new HashSet<String>();
		webNews = new WebScraping(newsLink);

		fakeNewsDataBase = new DataSetController();
		
		try {
			fakeNewsDataBase.startDataSet(dataSetFileName);
		} catch (DataSetNotFoundException e) {
			System.out.println("Aqui");
			addErrorMessage(e.getMessage());
		} catch (DataSetNoContentException e) {
			addErrorMessage(e.getMessage());
		} catch (Exception e) {
			addErrorMessage("Falha ao manipular DataSet");
		}
		
	}

	public void configAlgorithm(String algorithmName) {
		usedAlgorithms.add(algorithmName);
	}

	public double calculateSimilarutyPercentage() {
		int numberOfNews = fakeNewsDataBase.getNumberOfNews();

		for (int i = 0; i < numberOfNews; i++) {
			try {
				newSimilarity = new SimilaritySystemController(fakeNewsDataBase.getFakeNews(i + 1), webNews.getWebNews());
			} catch (DataSetNotFoundException e) {
				addErrorMessage(e.getLocalizedMessage());
			}

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

	private void addErrorMessage(String message) {
		System.out.println("Erro adicionado");
		toGUIWarnings.add(message);
	}

	public ArrayList<String> getErrorMessages() {
		return toGUIWarnings;
	}

}