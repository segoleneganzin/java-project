package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import piscine.Main;

public class PinCodeController {
	//TODO Ajouter lorsque clique des touches 0 à 9, remplir le fx:id:pincode du PinCode.fxml et, si clique sur # (diese) valider, mais au préalable, vérifier si le pincode entré contient bien 4 chiffres.
	   @FXML
	    private Button home;

	    @FXML
	    void home(ActionEvent event) {

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
