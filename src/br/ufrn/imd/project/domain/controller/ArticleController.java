/**
 * Arquivo contendo a class Article desenvolvida para armazenar as informaçõs de contidas no texto da notícia
 */
package br.ufrn.imd.project.domain.controller;

import java.util.ArrayList;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class ArticleController {
	private ArrayList<String> paragraphs = new ArrayList<String>(); /* Paragrafos do artigo já tratados */

	/**
	 * Informação que contem no artigo
	 * 
	 * @param content Conteúdo do paráfrafo inicial
	 */
	public ArticleController(String content) {
		addParagraph(content);		
	}

	/**
	 * Obter a informação do parágrafo já tratado do artigo
	 * 
	 * @param number Número do parágrafo solicitado do artigo
	 * @return Parágrafo solicitado
	 */
	protected String getParagraph(int number) {
		if ((number > getTotalNumberOfParagraph()) || (number <= 0)) {
			// retornar erro pois o número é maior que a quantidade de parágrafos
			return null;
		} else {
			int numberCorrection = number - 1;
			return paragraphs.get(numberCorrection);
		}
	}

	/**
	 * Adicioar novo parágrafo
	 * 
	 * @param text Conteudo do parágrafo
	 */
	protected void addParagraph(String text) {
		HandlingTextController formatedText = new HandlingTextController(text);
		formatedText.removeWordIfLessThan(4);
		formatedText.toLowerCaseText();
		formatedText.removeAccent();
		formatedText.removeSpecialCharacter();
		formatedText.removeRepeatedWords();
		formatedText.sortText();			
		
		paragraphs.add(formatedText.getText());
	}

	/**
	 * Verificar quantos paragrafos existem no artigo
	 * 
	 * @return Retorna número de parágrafos do artigo já armazenados
	 */
	protected int getTotalNumberOfParagraph() {
		return paragraphs.size();
	}
}
