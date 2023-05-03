package application;

import java.time.LocalDateTime;

import dao.CodeDAO;
import dao.UtilisationDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import piscine.Code;
import piscine.Utilisation;

public class SoldeController extends GeneralController{

	@FXML private Pane codeInfosContainer;
	@FXML private TextField code;
	@FXML private Label messageErreur;
	@FXML private Label dateAchat;
	@FXML private Label dateEcheance;
	@FXML private Label modaliteOffre;
	@FXML private Label solde;

	@FXML
	public void afficherCode() {
		try {
			String idCode = code.getText();
			//rechercher le code dans la bd
			Code unCode = CodeDAO.getInstance().read(idCode);
			if (unCode != null) {
				codeInfosContainer.setVisible(false);
				LocalDateTime echeance = unCode.getDateEcheance();
				//test si la date d'echeance est posterieure a "aujourd'hui" :
				if (echeance.isAfter(LocalDateTime.now())) {
					messageErreur.setVisible(false);
					codeInfosContainer.setVisible(true);
					dateAchat.setText(String.valueOf(unCode.getDateAchat()));
					dateEcheance.setText(String.valueOf(unCode.getDateEcheance()));
					modaliteOffre.setText(unCode.getOffre().getModalite());
					solde.setText(String.valueOf(unCode.getSoldeCode()));
				} else {
					codeInfosContainer.setVisible(false);
					messageErreur.setVisible(true);
					messageErreur.setText("Votre abonnement a expiré le "+echeance);
				}
			} else {
				codeInfosContainer.setVisible(false);
				messageErreur.setVisible(true);
				messageErreur.setText("Code inexistant, Veuillez réessayer !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}
