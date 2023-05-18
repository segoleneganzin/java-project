package application;

import java.time.LocalDateTime;

import dao.CodeDAO;
import dao.UtilisationDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import piscine.Code;
import piscine.Main;
import piscine.Piscine;
import piscine.Utilisation;

public class UtilisationController extends GeneralController{

	@FXML private TextField code;
	@FXML private Label messageErreur;
	@FXML private Label messageValide;
	//recuperation de la piscine stocke dans la classe mere :
	private Piscine piscine= GeneralController.getLaPiscine();


	@FXML
	public void validerPassage() {
		try {
			String idCode = code.getText();
			//rechercher le code dans la bd
			Code unCode = CodeDAO.getInstance().read(idCode);
			if (unCode != null) {
				messageErreur.setVisible(false);
				String modalite = unCode.getOffre().getModalite();
				int solde = unCode.getSoldeCode();
				LocalDateTime echeance = unCode.getDateEcheance();
				//test si la date d'echeance est posterieure a "aujourd'hui" :
				if (echeance.isAfter(LocalDateTime.now())) {
					//regarder si c'est un cours
					if (modalite.equals("cours")) {
						messageValide.setVisible(false);
						messageErreur.setVisible(true);
						messageErreur.setText("Votre code n'est valable que pour un cours");
					}
					//regarde si l'abonnement est solo et si il reste des entrees
					else if (modalite.equals("solo") && solde>1) {	
						messageErreur.setVisible(false);
						Utilisation utilisation = new Utilisation(LocalDateTime.now(), unCode, piscine);
						UtilisationDAO.getInstance().create(utilisation);
						Parent root = FXMLLoader.load(getClass().getResource("../ihm/BonneBaignade.fxml"));
						Scene scene = new Scene(root);
						Main.stage.setScene(scene);
						Main.stage.show();
					} 
					//regarde si l'abonnement est duo et si il reste des entrees
					else if (modalite.equals("duo") && solde>2) {				
						Utilisation utilisation1 = new Utilisation(LocalDateTime.now(), unCode, piscine);
						UtilisationDAO.getInstance().create(utilisation1);
						//ajoute 5 secondes car on ne peut avoir deux clé identiques (code+date)
						Utilisation utilisation2 = new Utilisation(LocalDateTime.now().plusSeconds(5), unCode, piscine);
						UtilisationDAO.getInstance().create(utilisation2);
						Parent root = FXMLLoader.load(getClass().getResource("../ihm/BonneBaignade.fxml"));
						Scene scene = new Scene(root);
						Main.stage.setScene(scene);
						Main.stage.show();
					} else {
						messageValide.setVisible(false);
						messageErreur.setVisible(true);
						messageErreur.setText("Vous n'avez plus d'entrées disponibles");
					}
				} else {
					messageValide.setVisible(false);
					messageErreur.setVisible(true);
					messageErreur.setText("Votre abonnement a expiré le "+ unCode.toStringDateEcheance());
				}

			} 
			else {
				messageValide.setVisible(false);
				messageErreur.setVisible(true);
				messageErreur.setText("Code inexistant, Veuillez réessayer !");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

