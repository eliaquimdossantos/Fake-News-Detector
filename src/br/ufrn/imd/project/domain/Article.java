/**
 * Arquivo contendo a class Article desenvolvida para armazenar as informa��s de contidas no texto da not�cia
 */
package br.ufrn.imd.project.domain;

import java.util.ArrayList;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class Article {
	private ArrayList<String> Paragraphs = new ArrayList<String>(); /* Paragrafos do artigo j� tratados */

	/**
	 * Informa��o que contem no artigo
	 * 
	 * @param content Conte�do do par�frafo inicial
	 */
	public Article(String content) {
		addParagraph(content);
	}

	/**
	 * Obter a informa��o do par�grafo j� tratado do artigo
	 * 
	 * @param number N�mero do par�grafo solicitado do artigo
	 * @return Par�grafo solicitado
	 */
	protected String getParagraph(int number) {
		if ((number > getTotalNumberOfParagraph()) || (number <= 0)) {
			// retornar erro pois o n�mero � maior que a quantidade de par�grafos
			return null;
		} else {
			int numberCorrection = number - 1;
			return Paragraphs.get(numberCorrection);
		}
	}

	/**
	 * Adicioar novo par�grafo
	 * 
	 * @param txt Conteudo do par�grafo
	 */
	protected void addParagraph(String txt) {
		HandlingParagraph text = new HandlingParagraph(txt);
		Paragraphs.add(text.getText());
	}

	/**
	 * Verificar quantos paragrafos existem no artigo
	 * 
	 * @return Retorna n�mero de par�frafos do artigo j� armazenados
	 */
	protected int getTotalNumberOfParagraph() {
		return Paragraphs.size();
	}
}
