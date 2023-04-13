package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import piscine.Main;

public class PaiementController {
	@FXML
	private Label montant;
	@FXML
	private void initialize() {
		// TODO Récupérer la valeur de la case de la page précédente
		montant.setText("essai de modif label");
	}
	@FXML
	private Button home;

	@FXML
	void home(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/Accueil.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	private Button validepay ;

	@FXML
	void validatepay(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/PinCode.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
