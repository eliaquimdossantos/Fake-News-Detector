package br.ufrn.imd.project.domain.controller;

import java.util.ArrayList;
import java.util.HashSet;

import br.ufrn.imd.project.domain.model.exception.DataSetNoContentException;
import br.ufrn.imd.project.domain.model.exception.DataSetUninformedException;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */

public class MainController {

	private HashSet<String> usedAlgorithms;
	private static ArrayList<String[]> toGUIWarnings;
	private DataSetController fakeNewsDataBase;
	WebScrapingController webNews;
	private double similarutyPercentage;
	SimilaritySystemController newsSimilarity;

	public MainController(String newsLink, String dataSetFileName) {
		toGUIWarnings = new ArrayList<String[]>();
		usedAlgorithms = new HashSet<String>();
		webNews = new WebScrapingController(newsLink);

		fakeNewsDataBase = new DataSetController();
		
		fakeNewsDataBase.startDataSet(dataSetFileName);		
		
	}

	/**
	 * Método usado para adicionar um novo tipo de algoritmo
	 * de semelhança entre strings
	 * 
	 * @param algorithmName Nome do algoritmo de semelhança (padrão CamelCase)
	 */
	public void configAlgorithm(String algorithmName) {
		System.out.println("Configurando o algoritmo " + algorithmName); // LOG
		usedAlgorithms.add(algorithmName);
	}

	/**
	 * Calcular a porcentagem de semelhança entre a notícia
	 * passada pelo link (webscapping) e as notícias cadastradas
	 * na base de dados (dataset) 
	 * 
	 * @return Porcentagem de semelhança entre os textos
	 */
	public double calculateSimilarutyPercentage() {
		System.out.println("Iniciando cálculo da porcentagem de semelhança"); // LOG
		int numberOfNews = fakeNewsDataBase.getNumberOfNews();		
		double similarutyValue = 0;

		for (int i = 0; i < numberOfNews; i++) {
			try {
				newsSimilarity = new SimilaritySystemController(fakeNewsDataBase.getFakeNews(i + 1), webNews.getWebNews());				
			} catch (DataSetUninformedException e) {
				addErrorMessage(e.getLocalizedMessage());
			} catch (DataSetNoContentException e) {
				addErrorMessage(e.getLocalizedMessage());
			}

			for (String algorithm : usedAlgorithms) {				
				newsSimilarity.addAlgorithm(algorithm);				
			}
			
			similarutyValue = newsSimilarity.startTests();
						 

			if (similarutyValue > similarutyPercentage) {
				similarutyPercentage = similarutyValue;
			}

			if (similarutyPercentage == 1.0) {
				break;
			}
		}

		System.out.println("A semelhança é de " + Math.floor(similarutyPercentage*100) + "%"); // LOG
		System.out.println("-----FIM------\n");
		return similarutyPercentage;

	}

	/**
	 * Método visível em todo o pacote
	 * @param Mensagem de erro a ser adicionada
	 */
	static void addErrorMessage(String message) {
		System.out.println("Erro encontrado: " + message); // LOG
		String[] messageError = message.split(",");		
		toGUIWarnings.add(messageError);		
	}

	/**
	 * Array com as mensagens de erro a serem
	 * disparadas na interface gráfica
	 * 
	 * @return ArrayList de vetores de String
	 */
	public ArrayList<String[]> getErrorMessages() {
		return toGUIWarnings;
	}

}