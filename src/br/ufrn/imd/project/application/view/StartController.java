/**
 * Class SampleController
 */
package br.ufrn.imd.project.application.view;

import javafx.scene.control.Slider;

import java.util.ArrayList;

import br.ufrn.imd.project.domain.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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

	private String defaultVoidTextFildColor; // Cor em caso do campo estar vazio

	private String defaultTextFildColor; // Cor padrão do campo de texto

	private String defaultColor; // Cor padrão do tema

	private String defaultFakeFoundColor; // Cor do tema caso encontre uma fake news

	private String defaultTrueFoundColor; // Cor do tema caso não seja encontrada uma fake news

	Alert alertDiag; // Responsável por mostrar mensagens de advertência na tela
	Alert errorDiag; // Responsável por mostrar mensagens de erro na tela

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
	private Pane sidebar;

	@FXML
	private Button verifyButton;

	@FXML
	private Button restartButton;

	@FXML
	private CheckBox trigramCheckBox;

	@FXML
	private CheckBox levenshteinCheckBox;

	@FXML
	private TextField dataSetDirectoryField;

	public StartController() {
		defaultColor = "#333645";
		defaultFakeFoundColor = "#b41600";
		defaultTrueFoundColor = "#01a662";
		defaultVoidTextFildColor = "#ffcdd9";
		defaultTextFildColor = "#e6e6e6";
	}

	@FXML
	/**
	 * Funcionamento do programa ao clicar no botão de verificar link
	 * 
	 * @param event Evento de mouse
	 */
	private void checkLink(MouseEvent event) {
		alertDiag = new Alert(Alert.AlertType.WARNING);
		errorDiag = new Alert(Alert.AlertType.ERROR);

		linkBar.setStyle("-fx-background-color:" + defaultTextFildColor);
		dataSetDirectoryField.setStyle("-fx-background-color:" + defaultTextFildColor);

		String link = linkBar.getText();
		String dataSetDirectory = dataSetDirectoryField.getText();

		if (link.isEmpty()) {
			linkBar.setStyle("-fx-background-color:" + defaultVoidTextFildColor);
			alertDiag.setTitle("Atenção");
			alertDiag.setHeaderText("Endereço de site não informado");
			alertDiag.setContentText(
					"Você deve informar o link válido de uma " + "notícia para que o resultado seja gerado.");
			alertDiag.show();
			return;
		}

		if (dataSetDirectory.isEmpty()) {
			dataSetDirectoryField.setStyle("-fx-background-color:" + defaultVoidTextFildColor);
			alertDiag.setTitle("Atenção");
			alertDiag.setHeaderText("Caminho da Base de Dados não informado!");
			alertDiag.setContentText(
					"Você deve informar o caminho no campo de " + "texto referente ao caminho da base de dados");
			alertDiag.show();
			return;
		}

		fakePercentageValue = start(link, dataSetDirectory);

		if (fakePercentageValue == -1) {
			onRestartStyle();
			return;
		}

		int fakePercentageLimiar = (int) Math.floor(similarutyLimiar.getValue());

		for (int i = 0; i <= 360; i++) {
			fakePercentage.setLength(fakePercentageValue * i);
		}

		if ((fakePercentageValue * 100) >= fakePercentageLimiar) {
			onFakeNewsDetectStyle();
			alertDiag.setTitle("Atenção");
			alertDiag.setHeaderText("Fake News detectada!");
			alertDiag.setContentText(
					"Sempre tenha a certeza de que uma notícia é " + "verdadeira antes de compartilha-la.");
			alertDiag.show();
		} else {
			onTrueNewsDetectStyle();
		}

		percentSimilarutyText.setText((int) (fakePercentageValue * 100) + "%");
	}

	@FXML
	/**
	 * Muda o conteúdo do Label ao lado do Slider (Que define o limiar) para o novo
	 * valor definido no Slider
	 * 
	 * @param event Evento de mouse
	 */
	private void checkSlider(MouseEvent event) {
		int limiarSeted = (int) similarutyLimiar.getValue();
		limiarText.setText(limiarSeted + "%");
	}

	@FXML
	/**
	 * Volta ao layout inicial
	 * 
	 * @param event Evento de mouse
	 */
	private void restart(MouseEvent event) {
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

		if (!trigramCheckBox.isSelected() && !levenshteinCheckBox.isSelected()) {
			trigramCheckBox.setSelected(true);
		}

		if (trigramCheckBox.isSelected()) {
			c.configAlgorithm("trigram");
		}

		if (levenshteinCheckBox.isSelected()) {
			c.configAlgorithm("levenshtein");
		}

		ArrayList<String[]> capturedErrors = c.getErrorMessages();

		for (String[] error : capturedErrors) {
			errorDiag.setTitle("Erro");
			errorDiag.setHeaderText(error[0]);
			errorDiag.setContentText(error[1]);
			errorDiag.show();

			return -1;
		}

		return c.calculateSimilarutyPercentage();
	}
}
