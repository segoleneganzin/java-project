package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import piscine.Cours;
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
	private Cours unCours;
	
	@FXML
	private void initialize() {
	}

	public Cours getUnCours() {
		return unCours;
	}

	public void setUnCours(Cours unCours) {
		this.unCours = unCours;
	}

	public void setModalite(String modalite) {
		this.modalite = modalite;
	}

	public String getModalite() {
		return modalite;
	}

	//recupere les infos de la page des abonnements
	public void setInfo(Offre uneOffre) {
		setModalite(uneOffre.getModalite());
		int tarifOffre = uneOffre.getTarif();
		String typeOffre = uneOffre.getModalite();
		typeAboChoisi.setText(typeOffre);
		montant.setText(String.valueOf(tarifOffre) + "€");
	}
	
	//recupere les infos de la page des cours
		public void setInfoCours(Cours unCours) {
			setUnCours(unCours);
		}



	@FXML
	void creerCode() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/PinCode.fxml"));
			root = loader.load();
			PinCodeController pinCodeController = loader.getController();
			pinCodeController.setInfo(getModalite());
			if (getModalite().equals("cours")) {
				pinCodeController.setInfoCours(getUnCours());
			}
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