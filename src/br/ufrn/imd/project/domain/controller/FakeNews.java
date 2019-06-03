/**
 * Arquivo com a class FakeNews com a estrutura de uma notíca falsa
 */
package br.ufrn.imd.project.domain.controller;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class FakeNews extends News {
	private String hash; /* Hash da notícia */

	/**
	 * Criar nova notícia falsa
	 * 
	 * @param link Link da notícia falsa
	 * @param date Data da postagem falsa
	 * @param text Texto contido na notícia falsa
	 */
	public FakeNews(String link, String date, String text) {
		super(link, date);

		this.article = new Article(text);

		this.hash = stringToHash(article.getParagraph(1));
	}

	/**
	 * Função para retornar a sha1
	 * 
	 * @return SHA1
	 */
	protected String getHash() {
		return hash;
	}

	/**
	 * Obter o parágrafo já tratado, desejado do artigo
	 * 
	 * @return Texto do artigo tratado
	 */
	protected String getParagraphFromArticle() {
		return article.getParagraph(1);
	}

}
