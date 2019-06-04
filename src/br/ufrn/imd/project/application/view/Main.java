/**
 * Class Main
 */
package br.ufrn.imd.project.application.view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

/**
 * @author ALLAN DE MIRANDA SILVA and ELIAQUIM DOS SANTOS COSTA
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {		
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
			Scene scene = new Scene(root, 720, 480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
