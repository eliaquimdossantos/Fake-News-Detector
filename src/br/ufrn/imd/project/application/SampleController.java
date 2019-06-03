/**
 * Class SampleController
 */
package br.ufrn.imd.project.application;

import javafx.scene.control.Slider;
import br.ufrn.imd.project.domain.controller.DataSetController;
import br.ufrn.imd.project.domain.controller.SimilaritySystem;
import br.ufrn.imd.project.domain.controller.WebScraping;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Arc;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class SampleController {
	
	private double fakePercentageValue; /* Guardar o valor da porcentagem da notícia ser fake */
	
	private String defaultColor;
	
	private String defaultFakeFoundColor;
	
	private String defaultTrueFoundColor;

	@FXML
	private TextField linkBar;
	
	@FXML
    private Slider similarutyLimiar;	
	
	@FXML
    private Arc fakePercentage;
	
	@FXML
    private Label percentSimilarutyText;
	
	@FXML
    private Label limiarText;

	@FXML
    private VBox sidebar;
	
	@FXML
    private Button verifyButton;
	
	@FXML
    private Button restartButton;
	
	public SampleController() {
		defaultColor = "#333645";
		defaultFakeFoundColor = "#b41600";
		defaultTrueFoundColor = "#01a662";
	}
	
	@FXML
	/**
	 * Funcionamento do programa ao clicar no botão de verificar link
	 * 
	 * @param event Evento de mouse
	 */
	void checkLink(MouseEvent event) {
		Alert fakeNewsAlert = new Alert(Alert.AlertType.INFORMATION);
		fakePercentageValue = calculatedSimilarutyPercentage(linkBar.getText(), "boatos.csv");
		int fakePercentageLimiar = (int) Math.floor(similarutyLimiar.getValue());
		
		for(int i = 0; i <= 360; i++) {			
			fakePercentage.setLength(fakePercentageValue*i);		
		}
		
		if ((fakePercentageValue*100) >=  fakePercentageLimiar) {			
			onFakeNewsDetectStyle();
			fakeNewsAlert.setTitle("Atenção");
			fakeNewsAlert.setHeaderText("Fake News detectada!");
			fakeNewsAlert.setContentText("Sempre tenha a certeza de que uma notícia é "
					+ "verdadeira antes de compartilha-la.");
			fakeNewsAlert.show();
		} else {
			onTrueNewsDetectStyle();
		}
		
		percentSimilarutyText.setText((int) (fakePercentageValue*100) + "%");		
		
		System.out.println(linkBar.getText());
		
	}
		
	@FXML
	/**
	 * Muda o conteúdo do Label ao lado do Slider (Que define o limiar) para o 
	 * novo valor definido no Slider
	 * 
	 * @param event Evento de mouse
	 */
	void checkSlider(MouseEvent event){		
		int limiarSeted = (int) similarutyLimiar.getValue();
		limiarText.setText(limiarSeted + "%");			
	}
	
	@FXML
	/**
	 * Volta ao layout inicial  
	 * 
	 * @param event Evento de mouse
	 */
	private void restart (MouseEvent event) {		
		onRestartStyle();		
		linkBar.setText("");
		percentSimilarutyText.setText("%");
		fakePercentage.setLength(360);
	}

	/**
	 * Muda o layout no caso de detectar notícia que seja fake
	 */
	private void onFakeNewsDetectStyle() {		
		percentSimilarutyText.setStyle("-fx-text-fill:" + defaultFakeFoundColor);
		sidebar.setStyle("-fx-background-color:" + defaultFakeFoundColor);
		verifyButton.setStyle("-fx-background-color:" + defaultFakeFoundColor);
		fakePercentage.setStyle("-fx-fill:" + defaultFakeFoundColor);
		restartButton.setStyle("-fx-background-color:" + defaultFakeFoundColor);
	}
	
	/**
	 * Muda o layout no caso de detectar notícia que não seja fake
	 */
	private void onTrueNewsDetectStyle() {		
		percentSimilarutyText.setStyle("-fx-text-fill:" + defaultTrueFoundColor);
		sidebar.setStyle("-fx-background-color:" + defaultTrueFoundColor);
		verifyButton.setStyle("-fx-background-color:" + defaultTrueFoundColor);
		fakePercentage.setStyle("-fx-fill:" + defaultTrueFoundColor);
		restartButton.setStyle("-fx-background-color:" + defaultTrueFoundColor);
	}
	
	/**
	 * Muda o layout para o padrão
	 */
	private void onRestartStyle() {		
		percentSimilarutyText.setStyle("-fx-text-fill:" + defaultColor);
		sidebar.setStyle("-fx-background-color:" + defaultColor);
		verifyButton.setStyle("-fx-background-color:" + defaultColor);
		fakePercentage.setStyle("-fx-fill:" + defaultColor);
		restartButton.setStyle("-fx-background-color:" + defaultColor);
	}
	
	/**
	 * Executar o sistema
	 * 
	 * @param link     Link da notícia
	 * @param fileName Caminho do arquivo data set
	 * @return Valor da porcentagem de ser fake a notícia
	 */	
	private double calculatedSimilarutyPercentage(String link, String fileName) {
		
		DataSetController dataBaseFake = new DataSetController();
		dataBaseFake.startDataSet(fileName);
		
		WebScraping webNews = new WebScraping(link);
		double calculatedSimilaruty = 0.0;
			
		int numberOfNews = dataBaseFake.getNumberOfNews();
		
		for (int i = 0; i < numberOfNews; i++) {
			SimilaritySystem newSimilarity = new SimilaritySystem(dataBaseFake.getFakeNews(i + 1),
					webNews.getWebNews());
			if (newSimilarity.getSimilarutyValue() > calculatedSimilaruty) {
				calculatedSimilaruty = newSimilarity.getSimilarutyValue();
			}
			
			if(calculatedSimilaruty == 1.0){
				break;
			}
		}
		
		return calculatedSimilaruty;
	}	
}
