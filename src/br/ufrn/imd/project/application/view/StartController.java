/**
 * Class SampleController
 */
package br.ufrn.imd.project.application.view;

import javafx.scene.control.Slider;
import br.ufrn.imd.project.domain.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Arc;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class StartController {
	
	private double fakePercentageValue; // Guardar o valor da porcentagem da notícia ser fake
	
	private String defaultColor; // Cor padrão do tema
	
	private String defaultFakeFoundColor; // Cor do tema caso encontre uma fake news
	
	private String defaultTrueFoundColor; // Cor do tema caso não seja encontrada uma fake news
	
	Alert alert; // Responsável por mostrar mensagens de alerta na tela

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
	
	@FXML
    private CheckBox trigramCheckBox;

    @FXML
    private CheckBox levenshteinCheckBox;
	
	public StartController() {
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
	private void checkLink(MouseEvent event) {
		alert = new Alert(Alert.AlertType.INFORMATION);
		String link = linkBar.getText();					
		
		if(link.isEmpty()) {
			alert.setTitle("Atenção");
			alert.setHeaderText("Endereço de site não informado");
			alert.setContentText("Você deve informar o link válido de uma notícia para que o resultado seja gerado.");
			alert.show();
			return;
		}
		
		fakePercentageValue = start(link, "boatos.csv");
				
		int fakePercentageLimiar = (int) Math.floor(similarutyLimiar.getValue());
		
		for(int i = 0; i <= 360; i++) {			
			fakePercentage.setLength(fakePercentageValue*i);		
		}
		
		if ((fakePercentageValue*100) >=  fakePercentageLimiar) {			
			onFakeNewsDetectStyle();
			alert.setTitle("Atenção");
			alert.setHeaderText("Fake News detectada!");
			alert.setContentText("Sempre tenha a certeza de que uma notícia é "
					+ "verdadeira antes de compartilha-la.");
			alert.show();
		} else {
			onTrueNewsDetectStyle();
		}
		
		percentSimilarutyText.setText((int) (fakePercentageValue*100) + "%");							
	}
		
	@FXML
	/**
	 * Muda o conteúdo do Label ao lado do Slider (Que define o limiar) para o 
	 * novo valor definido no Slider
	 * 
	 * @param event Evento de mouse
	 */
	private void checkSlider(MouseEvent event){		
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
	 * Calcular porcentagem indicadora de fake news
	 * 
	 * @param link     Link da notícia
	 * @param fileName Caminho do arquivo data set
	 * @return Valor da porcentagem de a notícia ser fake 
	 */	
	private double start(String link, String fileName) {
		MainController c = new MainController(link, fileName);
		
		c.configAlgorithm("trigram");
		
		if (!trigramCheckBox.isSelected()) {
			trigramCheckBox.setSelected(true);
		}
		
		if (levenshteinCheckBox.isSelected()) {
			c.configAlgorithm("levenshtein");
		}
						
		return c.calculateSimilarutyPercentage();		
	}	
}
