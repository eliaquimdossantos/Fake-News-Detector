/**
 * Arquivo com a class WebScraping que tem a função de capturar na web a notícia
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
	 * Campura de notícia da web
	 * 
	 * @param link Link da notícia
	 */
	public WebScraping(String link) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.news = new WebNews(link, dateFormat.format(date), collectParagraphs(link));
	}

	/**
	 * Capturar os parágrafos do link
	 * 
	 * @param link Link da notícia
	 * @return Parágrafos da notícia
	 */
	private ArrayList<String> collectParagraphs(String link) {
		ArrayList<String> paragraphs = new ArrayList<String>();
		try {
			Document doc = Jsoup.connect(link).get();
			Elements elementsTag = doc.select("p");
			for (Element element : elementsTag) {
				// Número mínimo de letras para não pegar texto que não é notícia
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
	 * Abrir notícia da web
	 * 
	 * @return A notícia da web
	 */
	public WebNews getWebNews() {
		return news;
	}

}
