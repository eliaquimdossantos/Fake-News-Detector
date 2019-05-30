/**
 * Arquivo com a class WebNew que contem a estrutura de uma notícia da web
 */
package br.ufrn.imd.project.domain;

import java.util.ArrayList;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class WebNews extends News {
	private ArrayList<String> hashTheMap; /* Lista de hash dos parágrafos da notícia */

	/**
	 * Nova notícia da web
	 * 
	 * @param link       Link da notícia da web
	 * @param date       Data da postagem da web
	 * @param paragraphs Parágrafos do artigo da notícia da web
	 */
	public WebNews(String link, String date, ArrayList<String> paragraphs) {
		super(link, date);

		boolean fist = true;
		for (String text : paragraphs) {
			if (fist == true) {
				HandlingParagraph formatedText = new HandlingParagraph(text);
				this.article = new Article(text);				
				this.hashTheMap = new ArrayList<String>();
				String hash = stringToHash(formatedText.getText());
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
	 * @param number Posição da hash
	 * @return A hash sha1
	 */
	protected String getHash(int number) {
		if ((number > getNumberOfHash()) || (number <= 0)) {
			// retornar erro pois não existe essa posisão
			return null;
		} else {
			int numberCorrection = number - 1;
			return hashTheMap.get(numberCorrection);
		}
	}

	/**
	 * Quantidade de hash que existe no vetor
	 * 
	 * @return Número total de hash do vetor
	 */
	protected int getNumberOfHash() {
		return hashTheMap.size();
	}

	/**
	 * Abrir o parágrafo desejado
	 * 
	 * @param number Número do paragrafo que deseja abrir
	 * @return Texto do parágrafo já tratado
	 */
	protected String getArticle(int number) {
		return article.getParagraph(number);
	}

	/**
	 * Número de parágrafos que existem no artigo
	 * 
	 * @return Número de parágrafos total
	 */
	protected int getNumberOfArticle() {
		return article.getTotalNumberOfParagraph();
	}

}
