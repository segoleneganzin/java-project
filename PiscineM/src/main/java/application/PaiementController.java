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
	private String entreeValeur;
	@FXML
	private Label montant;
	@FXML
	private void initialize() {
		System.out.println(entreeValeur);
		// TODO Récupérer la valeur de la case de la page précédente
		montant.setText("essai de modif label "+ getEntreeValeur());
	}
	@FXML
	private Button home;

	@FXML
	void home(ActionEvent event) {
		System.out.println(entreeValeur);
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
	public String getEntreeValeur() {
        return entreeValeur;
    }

    public void setEntreeValeur(String value) {
        entreeValeur = value;
    }
}