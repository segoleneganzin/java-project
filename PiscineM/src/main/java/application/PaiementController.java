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

public class PaiementController extends GeneralController{
	private Parent root;
	private String entreeValeur;
	@FXML private Label montant;
	@FXML private Label typeAboChoisi;
	@FXML private Button paiementToAbo;
	@FXML private Button validepay ;
	private String modalite;


	@FXML
	private void initialize() {
	}

	public void setModalite(String modalite) {
		this.modalite = modalite;
	}

	//recupere les infos de la page des abonnements
	public void setInfo(Offre uneOffre) {
		setModalite(uneOffre.getModalite());
		int tarifAbo = uneOffre.getTarif();
		String typeAbo = uneOffre.getModalite();
		typeAboChoisi.setText(typeAbo);
		montant.setText(String.valueOf(tarifAbo) + "€");
	}



	@FXML
	void creerCode() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/PinCode.fxml"));
			root = loader.load();
			PinCodeController pinCodeController = loader.getController();
			pinCodeController.setInfo(modalite);
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//fonction de retour a la page precedente
	@FXML
	void paiementToAbo(ActionEvent event) {
		System.out.println(entreeValeur);
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/Abonnement.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}