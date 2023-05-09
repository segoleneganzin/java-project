package application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.CodeDAO;
import dao.OffreDAO;
import dao.UtilisationDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import piscine.Code;
import piscine.Cours;
import piscine.Main;
import piscine.Offre;
import piscine.Utilisation;

public class PaiementController extends GeneralController {
	private Parent root;
	@FXML
	private Label montant;
	@FXML
	private Label typeAboChoisi;
	@FXML
	private Button paiementToAbo;
	@FXML
	private PasswordField pincode;
	@FXML
	private Button pinToPaiement;
	@FXML
	private Button valider;
	@FXML
	private Pane paiementPane;
	@FXML
	private Pane pinCodePane;
	private String modalite;
	private Cours unCours;
	private Code nouveauCode;
	private Code code;

	// recupere les infos de la page des abonnements
	public void setInfo(Offre uneOffre) {
		modalite = uneOffre.getModalite();
		int tarifOffre = uneOffre.getTarif();
		String typeOffre = uneOffre.getModalite();
		typeAboChoisi.setText(typeOffre);
		montant.setText(String.valueOf(tarifOffre) + "€");
	}

	// recupere les infos de la page des cours
	public void setInfoCours(Cours cours) {
		unCours = cours;
	}

	@FXML
	void creerCode(ActionEvent event) {
		paiementPane.setVisible(false);
		pinCodePane.setVisible(true);
	}

	@FXML
	void validerAchat(ActionEvent event) {
		if (event.getSource() instanceof Button) {
			Button clickedButton = (Button) event.getSource();
			String buttonLabel = clickedButton.getText();
			if (event.getSource() == valider && pincode.getLength() == 4) {
				try {
					Offre uneOffre = OffreDAO.getInstance().readModalite(modalite);
					if (modalite.equals("solo") || modalite.equals("duo")) {
						code = new Code(null, null, OffreDAO.getInstance().read(uneOffre.getIdOffre()));
						code.setDateAchat(LocalDateTime.now());
						CodeDAO.getInstance().create(code);
						nouveauCode = CodeDAO.getInstance().read(code.getIdCode());
					} else if (modalite.equals("cours")) {
						List<Cours> listeCours = new ArrayList<Cours>();
						Code code = new Code(null, null, OffreDAO.getInstance().read(uneOffre.getIdOffre()),
								listeCours);
						code.setDateAchat(LocalDateTime.now());
						// ajoute le cours selectionne dans le "code", pour le read du code
						code.getLesCours().add(unCours);
						CodeDAO.getInstance().create(code);
						// la date d'echeance est la date du cours
						code.setDateEcheance(unCours.getHoraireDebut());
						CodeDAO.getInstance().update(code);
						nouveauCode = CodeDAO.getInstance().read(code.getIdCode());
						// creer participation :
						CodeDAO.getInstance().ajouterParticipation(unCours, nouveauCode);
						// creer utilisation a la date du cours :
						Utilisation utilisation = new Utilisation(unCours.getHoraireDebut(), nouveauCode,
								unCours.getPiscine());
						UtilisationDAO.getInstance().create(utilisation);
					}
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/AffichageCode.fxml"));
					root = loader.load();
					AffichageCodeController affichageCodeController = loader.getController();
					affichageCodeController.setInfoCode(nouveauCode);
					Scene scene = new Scene(root);
					Main.stage.setScene(scene);
					Main.stage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (buttonLabel.equals("Cancel")) {
				// Annulation - efface le contenu de la text box
				pincode.setText("");
			} else if (!buttonLabel.equals("Valider") && pincode.getLength() < 4 || pincode.getLength() > 4) {
				// Ajout du chiffre dans le champs tant que pincode < 4 et pas de clique sur
				// Valider
				pincode.setText(pincode.getText() + buttonLabel);
			}
		}
	}

	// fonction de retour a la page precedente
	@FXML
	void paiementToAbo(ActionEvent event) {
		try {
			if (unCours != null) {
				root = FXMLLoader.load(getClass().getResource("../ihm/Cours.fxml"));
			} else {
				root = FXMLLoader.load(getClass().getResource("../ihm/Abonnement.fxml"));
			}
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}