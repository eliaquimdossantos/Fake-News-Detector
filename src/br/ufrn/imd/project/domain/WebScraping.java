/**
 * Arquivo com a class WebScraping que tem a fun��o de capturar na web a not�cia
 */
package br.ufrn.imd.project.domain;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class WebScraping {
	private WebNews news;

	/**
	 * Campura de not�cia da web
	 * 
	 * @param link Link da not�cia
	 */
	public WebScraping(String link) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.news = new WebNews(link, dateFormat.format(date), collectParagraphs(link));
	}

	/**
	 * Capturar os par�grafos do link
	 * 
	 * @param link Link da not�cia
	 * @return Par�grafos da not�cia
	 */
	private ArrayList<String> collectParagraphs(String link) {
		ArrayList<String> paragraphs = new ArrayList<String>();
		try {
			Document doc = Jsoup.connect(link).get();
			Elements elementsTag = doc.select("p");
			for (Element element : elementsTag) {
				// N�mero m�nimo de letras para n�o pegar texto que n�o � not�cia
				if (element.text().length() > 200) {
					paragraphs.add(element.text());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return paragraphs;
	}

	/**
	 * Abrir not�cia da web
	 * 
	 * @return A not�cia da web
	 */
	public WebNews getWebNews() {
		return news;
	}

}
