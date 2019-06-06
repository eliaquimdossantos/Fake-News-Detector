/**
 * Arquivo com a class SimilaritySystem que trata a similaridade entre duas String's
 */
package br.ufrn.imd.project.domain.controller;

import java.util.HashSet;

import br.ufrn.imd.project.domain.similarutyAlgorithms.Algorithm;
import br.ufrn.imd.project.domain.similarutyAlgorithms.AlgorithmLevenshtein;
import br.ufrn.imd.project.domain.similarutyAlgorithms.AlgorithmTrigram;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class SimilaritySystemController {

	private double similaruty; /* Similaridade entre as notícias */
	private HashSet<String> algorithms;
	private FakeNews newsFake;
	private WebNews newsWeb;

	/**
	 * Verificar similaridade
	 * 
	 * @param newsFake  Notícia fake
	 * @param newsWeb   Notícia da Web
	 * @param algorithm Nome do algoritmo que será usado (trigram, levenshtein )
	 */
	public SimilaritySystemController(FakeNews newsFake, WebNews newsWeb) {
		this.newsFake = newsFake;
		this.newsWeb = newsWeb;
		this.similaruty = 0;
		this.algorithms = new HashSet<String>();	
	}

	public void startTests() {
		for (int i = 0; i < newsWeb.getNumberOfArticle(); i++) {
			if (hashSimilarity(newsFake.getHash(), newsWeb.getHash(i + 1))) {
				similaruty = 1;
				break;
			}
		}		
		
		if (similaruty == 0) {
			for (int i = 0; i < newsWeb.getNumberOfArticle(); i++) {
				double newSimilaruty = calculateSimilaruty(newsFake.getParagraphFromArticle(),
						newsWeb.getArticle(i + 1));					
				
				if (newSimilaruty > similaruty) {
					similaruty = newSimilaruty;
				}
			}
		}
	}
	
	public void addAlgorithm(String algorithm) {
		String algorithmName = algorithm.toLowerCase();		

		algorithms.add(algorithmName);
	}

	/**
	 * Verificar similaridade entre hash's
	 * 
	 * @param hashFirst  Hash sha1
	 * @param hashSecond Hash sha1
	 * @return Se é similares
	 */
	private boolean hashSimilarity(String hashFirst, String hashSecond) {
		return hashFirst.equals(hashSecond);
	}

	/**
	 * Calculo de similaridade entre as notícias
	 * 
	 * @param textFirst  Paragráfo da notícia um
	 * @param textSecond Paragráfo da notícia dois
	 * @return Porcentagem de similaridade
	 */
	private double calculateSimilaruty(String textFirst, String textSecond) {				
		int numberOfUsedAlgorithms = algorithms.size();		
		double similaruty = 0;
		double testResult = 0;
		Algorithm a;
		
		/**
		 * Para cada algoritmo de semelhança, uma variável de controle
		 * como as abaixo deve ser adicionada
		 */
		double highestResultTrigram = 0;
		double highestResultLevenshtein = 0;

		for (String algorithm : algorithms) {
			if (algorithm.equals("trigram")) {				
				a = new AlgorithmTrigram();
				testResult = a.startTest(textFirst, textSecond);
				if(highestResultTrigram < testResult) {
					highestResultTrigram = testResult;
				}
			} else if (algorithm.equals("levenshtein")) {
				a = new AlgorithmLevenshtein();				
				testResult = a.startTest(textFirst, textSecond);				
				if(highestResultLevenshtein < testResult) {
					highestResultLevenshtein = testResult;
				}
			} else { // Algoritmo padrão
				a = new AlgorithmTrigram();
				testResult = a.startTest(textFirst, textSecond);
				if(highestResultTrigram < testResult) {
					highestResultTrigram = testResult;
				}
			}																								
		}							
			
		similaruty += highestResultTrigram;
		similaruty += highestResultLevenshtein;
		
		if (numberOfUsedAlgorithms > 0) {
			similaruty = similaruty / numberOfUsedAlgorithms;			
		} else if (similaruty > 1) {
			similaruty = 1;
		} else {
			similaruty = 0;
		}

		return similaruty;
	}

	/**
	 * Similaridade calculada
	 * 
	 * @return Porcentagem de similaridade
	 */
	public double getSimilarutyValue() {
		return similaruty;
	}

}
