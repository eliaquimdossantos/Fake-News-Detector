/**
 * Class SampleController
 */
package br.ufrn.imd.project.application;

import br.ufrn.imd.project.domain.DataSet;
import br.ufrn.imd.project.domain.SimilaritySystem;
import br.ufrn.imd.project.domain.WebScraping;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class SampleController {
	private String percentageFakeValue; /* Guardar o valor da porcentagem da notícia ser fake */

	/**
	 * Execultar o sistema
	 * 
	 * @param link     Link da notícia
	 * @param fileName Caminho do arquivo data set
	 * @return Valor da porcentagem de ser fake a notícia
	 */
	private String Start(String link, String fileName) {
		DataSet dataBaseFake = new DataSet(fileName);
		WebScraping webNews = new WebScraping(link);

		double magicSimilarity = 0.0;
		for (int i = 0; i < dataBaseFake.numberOfNews(); i++) {
			SimilaritySystem newSimilarity = new SimilaritySystem(dataBaseFake.getFakeNews(i + 1),
					webNews.getWebNews());
			if (newSimilarity.getSimilarutyValue() > magicSimilarity) {
				magicSimilarity = newSimilarity.getSimilarutyValue();
			}
		}

		System.out.println(Double.toString(magicSimilarity));

		return Integer.toString((int) Math.floor(magicSimilarity * 100));			
	}

	@FXML
	private TextField linkBar;

	@FXML
	private Text percentageFake;

	@FXML
	void checkLink(MouseEvent event) {
		percentageFakeValue = Start(linkBar.getText(), "./data/boatos.csv");
		percentageFake.setText(percentageFakeValue + "%");
		System.out.println(linkBar.getText());
	}

}
