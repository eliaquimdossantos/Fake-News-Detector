package br.ufrn.imd.project.domain.controller;

import java.util.ArrayList;
import java.util.HashSet;

import br.ufrn.imd.project.domain.model.exception.DataSetNoContentException;
import br.ufrn.imd.project.domain.model.exception.DataSetNotFoundException;

public class MainController {

	private HashSet<String> usedAlgorithms;
	private static ArrayList<String[]> toGUIWarnings;
	private DataSetController fakeNewsDataBase;
	WebScraping webNews;
	private double similarutyPercentage;
	SimilaritySystemController newSimilarity;

	public MainController(String newsLink, String dataSetFileName) {
		toGUIWarnings = new ArrayList<String[]>();
		usedAlgorithms = new HashSet<String>();
		webNews = new WebScraping(newsLink);

		fakeNewsDataBase = new DataSetController();
		
		try {
			fakeNewsDataBase.startDataSet(dataSetFileName);
		} catch (DataSetNotFoundException e) {			
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
		double similarutyValue = 0;

		for (int i = 0; i < numberOfNews; i++) {
			try {
				newSimilarity = new SimilaritySystemController(fakeNewsDataBase.getFakeNews(i + 1), webNews.getWebNews());				
			} catch (DataSetNotFoundException e) {
				addErrorMessage(e.getLocalizedMessage());
			} catch (DataSetNoContentException e) {
				addErrorMessage(e.getLocalizedMessage());
			}

			for (String algorithm : usedAlgorithms) {				
				newSimilarity.addAlgorithm(algorithm);				
			}
			
			newSimilarity.startTests();
			
			similarutyValue = newSimilarity.getSimilarutyValue();

			if (similarutyValue > similarutyPercentage) {
				similarutyPercentage = similarutyValue;
			}

			if (similarutyPercentage == 1.0) {
				break;
			}
		}

		return similarutyPercentage;

	}

	/**
	 * Método visível em todo o pacote
	 * @param Mensagem de erro a ser adicionada
	 */
	static void addErrorMessage(String message) {
		String[] messageError = message.split(",");		
		toGUIWarnings.add(messageError);		
	}

	public ArrayList<String[]> getErrorMessages() {
		return toGUIWarnings;
	}

}