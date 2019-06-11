/**
 * Arquivo com a class SimilaritySystem que trata a similaridade entre duas String's
 */
package br.ufrn.imd.project.domain.controller;

import java.util.HashSet;

import br.ufrn.imd.project.domain.similarutyAlgorithms.Algorithm;
import br.ufrn.imd.project.domain.similarutyAlgorithms.AlgorithmFactory;
import br.ufrn.imd.project.domain.similarutyAlgorithms.AlgorithmLevenshteinFactory;
import br.ufrn.imd.project.domain.similarutyAlgorithms.AlgorithmTrigramFactory;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class SimilaritySystemController {

	private double similaruty; /* Similaridade entre as notícias */
	private HashSet<String> algorithms;
	private FakeNewsController newsFake;
	private WebNewsController newsWeb;

	/**
	 * Verificar similaridade
	 * 
	 * @param newsFake  Notícia fake
	 * @param newsWeb   Notícia da Web
	 * @param algorithm Nome do algoritmo que será usado (trigram, levenshtein )
	 */
	public SimilaritySystemController(FakeNewsController newsFake, WebNewsController newsWeb) {
		this.newsFake = newsFake;
		this.newsWeb = newsWeb;
		this.similaruty = 0;
		this.algorithms = new HashSet<String>();
	}

	/**
	 * Adicionar um novo algoritmo de similaridade
	 * 
	 * @param algorithm Nome do algoritmo de similaridade
	 */
	public void addAlgorithm(String algorithm) {
		algorithms.add(algorithm.toLowerCase());
	}

	public double startTests() {	
		int numberOfArticles = newsWeb.getNumberOfArticle();
		boolean isSimilar;
		
		for (int i = 0; i < numberOfArticles; i++) {
			isSimilar = hashSimilarity(newsFake.getHash(), newsWeb.getHash(i + 1));
			if (isSimilar) {
				similaruty = 1;
				break;
			}
		}

		if (similaruty == 0) {
			for (int i = 0; i < newsWeb.getNumberOfArticle(); i++) {
				double newSimilaruty = calculateSimilaruty(
						newsFake.getParagraphFromArticle(),
						newsWeb.getArticle(i + 1)
					);

				if (newSimilaruty > similaruty) {
					similaruty = newSimilaruty;
				}
			}
		}
		return similaruty;
	}

	/**
	 * Verificar similaridade entre hash's
	 * 
	 * @param hashFirst  Hash sha1
	 * @param hashSecond Hash sha1
	 * @return Se é similares
	 */
	public boolean hashSimilarity(String hashFirst, String hashSecond) {
		return hashFirst.equals(hashSecond);
	}

	/**
	 * Calculo de similaridade entre as notícias
	 * 
	 * @param textFirst  Paragráfo da notícia um
	 * @param textSecond Paragráfo da notícia dois
	 * @param algorithm  Algoritmo a ser usado no cálculo da similaridade
	 * @return Porcentagem de similaridade
	 */
	public double calculateSimilaruty(String textFirst, String textSecond, String algorithm) {		
		double similaruty = 0;
		double testResult = 0;
		AlgorithmFactory algFact = null;

		// Ao implementar um novo algoritmo, basta adicionar
		// o 'case' correspondente
		switch (algorithm) {
		case "trigram":
			algFact = new AlgorithmTrigramFactory();
			break;
		case "levenshtein":
			algFact = new AlgorithmLevenshteinFactory();
			break;
		}

		Algorithm a = algFact.create();
		testResult = a.startTest(textFirst, textSecond);		
		
		/**
		 * Garante que o grau de semelhança não vai
		 * ultrapassar os limites entre 0% e 100%
		 */
		if (testResult > 1) {
			similaruty = 1;
		} else if (testResult < 0) {					
			similaruty = 0;
		} else {
			similaruty = testResult;
		}
		
		return similaruty;
	}
	
	/**
	 * Calculo de similaridade entre as notícias
	 * 
	 * @param textFirst  Paragráfo da notícia um
	 * @param textSecond Paragráfo da notícia dois
	 * @param algorithm  Algoritmo a ser usado no cálculo da similaridade
	 * @return Porcentagem de similaridade
	 */
	public double calculateSimilaruty(String textFirst, String textSecond) {		
		double similaruty = 0;
		double testResult = 0;
		AlgorithmFactory algFact = null;

		// TODO Ao implementar um novo algoritmo, basta adicionar o 'if' correspondente
		for(String algorithm : algorithms) {
			if(algorithm.equals("trigram")) {
				algFact = new AlgorithmTrigramFactory();
			} else if(algorithm.equals("levenshtein")) {
				algFact = new AlgorithmLevenshteinFactory();
			} // TODO Adicione a partir daqui o próximo algoritmo
			
			Algorithm a = algFact.create();
			testResult += a.startTest(textFirst, textSecond);
		}

		testResult /= getNumberOfAddedAlgorithms();
		
		/**
		 * Garante que o grau de semelhança não vai
		 * ultrapassar os limites entre 0% e 100%
		 */
		if (testResult > 1) {
			similaruty = 1;
		} else if (testResult < 0) {					
			similaruty = 0;
		} else {
			similaruty = testResult;
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
	
	public int getNumberOfAddedAlgorithms() {
		return algorithms.size();
	}
}
