package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import piscine.Main;

public class TicketController {
	public String entreeValeur;
	@FXML
	private Button TktToAcc;

	@FXML
	void TktToAcc(ActionEvent event) {

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
	private Button entree1;

	@FXML
	void entree1(ActionEvent event) {
		try {
			entreeValeur = "2 euros";
			PaiementController paiementController = new PaiementController();
			paiementController.setEntreeValeur("essai");
			System.out.println(entreeValeur);
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/Paiement.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button entree2;

	@FXML
	void entree2(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/Paiement.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button entree5;

	@FXML
	void entree5(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/Paiement.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button entree10;

	@FXML
	void entree10(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/Paiement.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
