/**
 * Arquivo com a class SimilaritySystem que trata a similaridade entre duas String's
 */
package br.ufrn.imd.project.domain;

import java.util.ArrayList;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class SimilaritySystem {

	private double similaruty; /* Similaridade entre as notícias */

	/**
	 * Verificar similaridade
	 * 
	 * @param newsFake Notícia fake
	 * @param newsWeb  Notícia da Web
	 */
	public SimilaritySystem(FakeNews newsFake, WebNews newsWeb) {
		this.similaruty = 0;
		
		System.out.println(newsFake);
		System.out.println(newsWeb);
		System.out.println("");
		
		for (int i = 0; i < newsWeb.getNumberOfArticle(); i++) {
			if (hashSimilarity(newsFake.getHash(), newsWeb.getHash(i + 1))) {
				similaruty = 1;
				break;
			}
		}
		
		if (similaruty == 0) {
			for (int i = 0; i < newsWeb.getNumberOfArticle(); i++) {
				double newSimilaruty = trigram(newsFake.getParagraphFromArticle(), newsWeb.getArticle(i + 1));
				if (newSimilaruty > similaruty) {
					similaruty = newSimilaruty;
				}
			}
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
	 * @param textFirst Paragráfo da notícia um
	 * @param textSecond  Paragráfo da notícia dois
	 * @return Porcentagem de similaridade
	 */
	private double trigram(String textFirst, String textSecond) {
		char[] firstAll = textFirst.toCharArray();
		ArrayList<String> firstWordList = new ArrayList<String>();
		for (int i = 0; i < (firstAll.length - 2); i++) {
			firstWordList.add("" + firstAll[i] + firstAll[i + 1] + firstAll[i + 2]);
		}

		char[] secondAll = textSecond.toCharArray();
		ArrayList<String> secondWordList = new ArrayList<String>();
		for (int i = 0; i < (secondAll.length - 2); i++) {
			secondWordList.add("" + secondAll[i] + secondAll[i + 1] + secondAll[i + 2]);
		}

		double numberOfCommon = 0;
		for (String slow : firstWordList) {
			for (String fast : secondWordList) {
				if (slow.equals(fast)) {
					numberOfCommon++;
				}
			}
		}
		double numberOfTotal = firstWordList.size() + secondWordList.size() - numberOfCommon;

		return (numberOfCommon / numberOfTotal);
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
