package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import piscine.Main;

public class GeneralController {
	
	//fonction de retour a la page d'accueil
	//stockee ici pour ne pas la repeter dans tous les controller
	@FXML
	void retourAcc(ActionEvent event) {
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

