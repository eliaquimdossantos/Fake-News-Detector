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

	/**
	 * Verificar similaridade
	 * 
	 * @param newsFake  Notícia fake
	 * @param newsWeb   Notícia da Web
	 * @param algorithm Nome do algoritmo que será usado (trigram, levenshtein )
	 */
	public SimilaritySystemController(FakeNews newsFake, WebNews newsWeb) {
		this.similaruty = 0;
		this.algorithms = new HashSet<String>();

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

		if (algorithmName.equals("levenshtein") 
				|| algorithmName.equals("trigram")) {
			algorithms.add(algorithmName);
		}
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
		Algorithm a;
		AlgorithmTrigram tr = new AlgorithmTrigram();
		int numberOfUsedAlgorithms = 0;

		for (String algorithm : algorithms) {
			if (algorithm.equals("trigram")) {
				a = new AlgorithmTrigram();
			} else if (algorithm.equals("levenshtein")) {
				a = new AlgorithmLevenshtein();
			} else {
				a = new AlgorithmTrigram();
			}			
			
			//similaruty += a.startTest(textFirst, textSecond);											
		}					
		
		similaruty = tr.startTest(textFirst, textSecond);
		
		numberOfUsedAlgorithms = 1;		

		if (numberOfUsedAlgorithms > 0) {
			similaruty = similaruty / numberOfUsedAlgorithms;
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
