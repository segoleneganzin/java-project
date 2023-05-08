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
import javafx.scene.control.PasswordField;
import piscine.Code;
import piscine.Cours;
import piscine.Main;
import piscine.Offre;
import piscine.Utilisation;

public class PinCodeController {
	private Parent root;
	private String modalite;
	private Cours leCours;
	private Code code;
	private Code nouveauCode;
	@FXML private PasswordField pincode;
	@FXML private Button pinToPaiement;
	@FXML private Button valider;


	public void setModalite(String modalite) {
		this.modalite = modalite;
	}
	
	public String getModalite() {
		return modalite;
	}

	public void setLeCours(Cours leCours) {
		this.leCours = leCours;
	}
	
	public Cours getLeCours() {
		return leCours;
	}

	//recupere les infos de la page des abonnements
	public void setInfo(String modalite) {
		setModalite(modalite);
	}
	

	public void setInfoCours(Cours cours) {
		setLeCours(cours);
	}


	@FXML
	void validerAchat(ActionEvent event) {
		if(event.getSource() instanceof Button) {
			Button clickedButton = (Button) event.getSource();
			String buttonLabel = clickedButton.getText();
			if(event.getSource() == valider && pincode.getLength()==4) {
				try {
					Offre uneOffre = OffreDAO.getInstance().readModalite(getModalite());
					if (getModalite().equals("solo") || getModalite().equals("duo")) {
						code = new Code(null, null, OffreDAO.getInstance().read(uneOffre.getIdOffre()));
						code.setDateAchat(LocalDateTime.now());
						CodeDAO.getInstance().create(code);
						nouveauCode = CodeDAO.getInstance().read(code.getIdCode());
					} else if (getModalite().equals("cours")) {
						List<Cours> listeCours= new ArrayList<Cours>();
						Code code = new Code(null, null, OffreDAO.getInstance().read(uneOffre.getIdOffre()), listeCours);
						code.setDateAchat(LocalDateTime.now());
						//ajoute le cours selectionne dans le "code", pour le read du code
						code.getLesCours().add(getLeCours());
						CodeDAO.getInstance().create(code);
						//la date d'echeance est la date du cours
						code.setDateEcheance(getLeCours().getHoraireDebut());
						CodeDAO.getInstance().update(code);
						nouveauCode = CodeDAO.getInstance().read(code.getIdCode());
						//creer participation :
						CodeDAO.getInstance().ajouterParticipation(getLeCours(), nouveauCode);
						//creer utilisation a la date du cours :
						Utilisation utilisation = new Utilisation(getLeCours().getHoraireDebut(), nouveauCode, getLeCours().getPiscine());
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
			} else if(buttonLabel.equals("Cancel")) {
				// Annulation - efface le contenu de la text box
				pincode.setText("");
			} 
			else if (!buttonLabel.equals("Valider") && pincode.getLength()<4 || pincode.getLength()>4){
				// Ajout du chiffre dans le champs tant que pincode < 4 et pas de clique sur Valider
				pincode.setText(pincode.getText() + buttonLabel);
			}
		}
	}   

	//fonction de retour a la page precedente
	@FXML
	void pinToPaiement(ActionEvent event) {
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