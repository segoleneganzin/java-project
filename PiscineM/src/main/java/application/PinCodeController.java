package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import piscine.Main;

public class PinCodeController {

	@FXML
	private PasswordField pincode;

	@FXML
	void pincodeTape(ActionEvent event) {
		if (event.getSource() instanceof Button) {
			Button clickedButton = (Button) event.getSource();
			String buttonLabel = clickedButton.getText();
//        	System.out.println(buttonLabel);	        
//	        System.out.println(pincode.getText());
//	        System.out.println(pincode.getLength());
			if (buttonLabel.equals("Valider") && pincode.getLength() >= 4) {
				// Validation du pincode affichage de la page transaction.fxml
				try {
					Parent root = FXMLLoader.load(getClass().getResource("../ihm/Transaction.fxml"));
					Scene scene = new Scene(root);
					Main.stage.setScene(scene);
					Main.stage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (buttonLabel.equals("Cancel")) {
				// Annulation - efface le contenu de la text box
				pincode.setText("");
			} else if (!buttonLabel.equals("Valider") && pincode.getLength() < 4) {
				// Ajout du chiffre dans le champs tant que pincode < 4 et pas de clique sur
				// Valider
				pincode.setText(pincode.getText() + buttonLabel);

			}
		}
	}

	@FXML
	private Button home;

	@FXML
	void homeClique(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/Accueil.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}