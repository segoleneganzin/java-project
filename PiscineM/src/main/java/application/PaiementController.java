package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import piscine.Main;
import piscine.Offre;

public class PaiementController {
	private String entreeValeur;
	@FXML
	private Label montant;
	@FXML
	private Label typeAboChoisi;
	@FXML
	private Button home;
	@FXML
	private Button validepay;

	@FXML
	private void initialize() {
	}

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

	// recupere les infos de la page des abonnements
	public void setInfo(Offre uneOffre) {
		int tarifAbo = uneOffre.getTarif();
		String typeAbo = uneOffre.getModalite();
		typeAboChoisi.setText(typeAbo);
		montant.setText(String.valueOf(tarifAbo) + "€");
	}

}