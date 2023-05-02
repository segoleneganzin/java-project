package application;

import dao.OffreDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import piscine.Main;
import piscine.Offre;

public class AboController {
	private Parent root;
	@FXML private int tarifAbo;
	@FXML private Pane descriptionAboSolo;
	@FXML private Pane descriptionAboDuo;
	@FXML private Label nbEntreeSolo;
	@FXML private Label validiteSolo;
	@FXML private Label tarifSolo;
	@FXML private Label nbEntreeDuo;
	@FXML private Label validiteDuo;
	@FXML private Label tarifDuo;
	@FXML private Button voirSolo;
	@FXML private Button achatSolo;
	@FXML private Button voirDuo;
	@FXML private Button achatDuo;
	@FXML private Button AboToAcc;


	@FXML
	void AboToAcc(ActionEvent event) {
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
	public void afficherAboSolo() {
		Offre uneOffre = OffreDAO.getInstance().readModalite("solo");
		voirSolo.setVisible(false);
		descriptionAboSolo.setVisible(true);
		nbEntreeSolo.setText(String.valueOf(uneOffre.getNbPlaces()));
		validiteSolo.setText(String.valueOf(uneOffre.getValidite()) + " mois");
		tarifSolo.setText(String.valueOf(uneOffre.getTarif()) + "€");
	}

	@FXML
	public void afficherAboDuo() {
		voirDuo.setVisible(false);
		descriptionAboDuo.setVisible(true);
		Offre uneOffre = OffreDAO.getInstance().readModalite("duo");
		nbEntreeDuo.setText(String.valueOf(uneOffre.getNbPlaces()));
		validiteDuo.setText(String.valueOf(uneOffre.getValidite()) + " mois");
		tarifDuo.setText(String.valueOf(uneOffre.getTarif()) + "€");
	}

	@FXML
	void acheterAbo(ActionEvent event) {
		try {
			//si offre solo :
			if(event.getSource() == achatSolo) {
				Offre uneOffre = OffreDAO.getInstance().readModalite("solo");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/Paiement.fxml"));
				root = loader.load();
				PaiementController paiementController = loader.getController();
				paiementController.setInfo(uneOffre);
			} 
			//si offre duo :
			if (event.getSource() == achatDuo) {
				Offre uneOffre = OffreDAO.getInstance().readModalite("duo");
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/Paiement.fxml"));
				root = loader.load();
				PaiementController paiementController = loader.getController();
				paiementController.setInfo(uneOffre);
			}
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}