/**
 * Arquivo com a class WebNew que contem a estrutura de uma not�cia da web
 */
package br.ufrn.imd.project.domain;

import java.util.ArrayList;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class WebNews extends News {
	private ArrayList<String> hashTheMap; /* Lista de hash dos par�grafos da not�cia */

	/**
	 * Nova not�cia da web
	 * 
	 * @param link       Link da not�cia da web
	 * @param date       Data da postagem da web
	 * @param paragraphs Par�grafos do artigo da not�cia da web
	 */
	public WebNews(String link, String date, ArrayList<String> paragraphs) {
		super(link, date);

		boolean fist = true;
		for (String text : paragraphs) {
			if (fist == true) {
				this.article = new Article(text);

				this.hashTheMap = new ArrayList<String>();
				String hash = stringToHash(text);
				hashTheMap.add(hash);

				fist = false;
			} else {
				article.addParagraph(text);
				String hash = stringToHash(text);
				hashTheMap.add(hash);
			}
		}
	}

	/**
	 * 
	 * @param number Posi��o da hash
	 * @return A hash sha1
	 */
	protected String getHash(int number) {
		if ((number > getNumberOfHash()) || (number <= 0)) {
			// retornar erro pois n�o existe essa posi��o
			return null;
		} else {
			int numberCorrection = number - 1;
			return hashTheMap.get(numberCorrection);
		}
	}

	/**
	 * Quantidade de hash que existe no vetor
	 * 
	 * @return N�mero total de hash do vetor
	 */
	protected int getNumberOfHash() {
		return hashTheMap.size();
	}

	/**
	 * Abrir o par�grafo desejado
	 * 
	 * @param number N�mero do paragrafo que deseja abrir
	 * @return Texto do par�grafo j� tratado
	 */
	protected String getArticle(int number) {
		return article.getParagraph(number);
	}

	/**
	 * N�mero de par�grafos que existem no artigo
	 * 
	 * @return N�mero de par�grafos total
	 */
	protected int getNumberOfArticle() {
		return article.getTotalNumberOfParagraph();
	}

}
