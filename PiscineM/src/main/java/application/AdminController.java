package application;

import dao.OffreDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import piscine.Main;
import piscine.Offre;

public class AdminController extends GeneralController {
	@FXML private Button connexion;
	@FXML private Text nbEntreeSoloAct;
	@FXML private Text validiteSoloAct;
	@FXML private Text tarifSoloAct;
	@FXML private Text nbEntreeDuoAct;
	@FXML private Text validiteDuoAct;
	@FXML private Text tarifDuoAct;

	@FXML private TextField nbEntreeSolo;
	@FXML private TextField validiteSolo;
	@FXML private TextField tarifSolo;
	@FXML private TextField nbEntreeDuo;
	@FXML private TextField validiteDuo;
	@FXML private TextField tarifDuo;
	private Integer idSolo;
	private Integer idDuo;

	@FXML
	private void initialize() {
		// TODO Recuperer login actuel
		Offre uneOffreDuo = OffreDAO.getInstance().readModalite("duo");
		idDuo=uneOffreDuo.getIdOffre();
		nbEntreeDuoAct.setText(String.valueOf(uneOffreDuo.getNbPlaces()));
		validiteDuoAct.setText(String.valueOf(uneOffreDuo.getValidite()));
		tarifDuoAct.setText(String.valueOf(uneOffreDuo.getTarif()));
		Offre uneOffreSolo = OffreDAO.getInstance().readModalite("solo");
		nbEntreeSoloAct.setText(String.valueOf(uneOffreSolo.getNbPlaces()));
		validiteSoloAct.setText(String.valueOf(uneOffreSolo.getValidite()));
		tarifSoloAct.setText(String.valueOf(uneOffreSolo.getTarif()));
		idSolo=uneOffreSolo.getIdOffre();
	}
	@FXML
	private void updateAboSolo() {
		int id = idSolo ;
		int validite;
		if (validiteSolo.getText().isEmpty()) {
			validite= Integer.parseInt(validiteSoloAct.getText());
		} else {
			validite= Integer.parseInt(validiteSolo.getText());
		}
		int tarif;
		if (tarifSolo.getText().isEmpty()) {
			tarif= Integer.parseInt(tarifSoloAct.getText());
		} else {
			tarif= Integer.parseInt(tarifSolo.getText());
		}
		int nbPlace;
		if (nbEntreeSolo.getText().isEmpty()) {
			nbPlace= Integer.parseInt(nbEntreeSoloAct.getText());
		} else {
			nbPlace= Integer.parseInt(nbEntreeSolo.getText());
		}
		Offre majOffreSolo = new Offre(id, validite, tarif, nbPlace, "solo");
		OffreDAO.getInstance().update(majOffreSolo);
		nbEntreeSolo.clear();
		validiteSolo.clear();
		tarifSolo.clear();
		this.initialize();
	}
	@FXML
	private void updateAboDuo() {
		int id = idDuo ;
		int validite;
		if (validiteDuo.getText().isEmpty()) {
			validite= Integer.parseInt(validiteDuoAct.getText());
		} else {
			validite= Integer.parseInt(validiteDuo.getText());
		}
		int tarif;
		if (tarifDuo.getText().isEmpty()) {
			tarif= Integer.parseInt(tarifDuoAct.getText());
		} else {
			tarif= Integer.parseInt(tarifDuo.getText());
		}
		int nbPlace;
		if (nbEntreeDuo.getText().isEmpty()) {
			nbPlace= Integer.parseInt(nbEntreeDuoAct.getText());
		} else {
			nbPlace= Integer.parseInt(nbEntreeDuo.getText());
		}
		Offre majOffreDuo = new Offre(id, validite, tarif, nbPlace, "duo");
		OffreDAO.getInstance().update(majOffreDuo);
		nbEntreeDuo.clear();
		validiteDuo.clear();
		tarifDuo.clear();
		this.initialize();
	}
	@FXML
	private void goToMajCours() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/UpdateCours.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}