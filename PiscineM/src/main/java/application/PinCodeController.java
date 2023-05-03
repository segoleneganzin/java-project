package application;

import java.time.LocalDateTime;

import dao.CodeDAO;
import dao.OffreDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import piscine.Code;
import piscine.Main;
import piscine.Offre;

public class PinCodeController {

	private String modalite;
	@FXML private PasswordField pincode;
	@FXML private Button pinToPaiement;


	public void setModalite(String modalite) {
		this.modalite = modalite;
	}

	//recupere les infos de la page des abonnements
	public void setInfo(String modalite) {
		setModalite(modalite);
	}


	@FXML
	void validerAchat(ActionEvent event) {
		if(event.getSource() instanceof Button) {
			Button clickedButton = (Button) event.getSource();
			String buttonLabel = clickedButton.getText();
			if(event.getSource() == "valider" && pincode.getLength()==4) {
				try {
					Offre uneOffre = OffreDAO.getInstance().readModalite(modalite);
					Code code = new Code(null, null, OffreDAO.getInstance().read(uneOffre.getIdOffre()));
					code.setDateAchat(LocalDateTime.now());
					CodeDAO.getInstance().create(code);
					Parent root = FXMLLoader.load(getClass().getResource("../ihm/Transaction.fxml"));
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