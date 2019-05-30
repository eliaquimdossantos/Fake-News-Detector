/**
 * Arquivo com a class FakeNews com a estrutura de uma not�ca falsa
 */
package br.ufrn.imd.project.domain;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class FakeNews extends News {
	private String hash; /* Hash da not�cia */

	/**
	 * Criar nova not�cia falsa
	 * 
	 * @param link Link da not�cia falsa
	 * @param date Data da postagem falsa
	 * @param text Texto contido na not�cia falsa
	 */
	public FakeNews(String link, String date, String text) {
		super(link, date);

		this.article = new Article(text);

		this.hash = stringToHash(article.getParagraph(1));
	}

	/**
	 * Fun��o para retornar a sha1
	 * 
	 * @return SHA1
	 */
	protected String getHash() {
		return hash;
	}

	/**
	 * Obter o par�grafo j� tratado, desejado do artigo
	 * 
	 * @return Texto do artigo tratado
	 */
	protected String getParagraphFromArticle() {
		return article.getParagraph(1);
	}

}
