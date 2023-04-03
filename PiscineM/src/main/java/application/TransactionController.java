package application;

import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import piscine.Main;

public class TransactionController {
	    @FXML
	    void home(ActionEvent event) {

	        try {
	        	TimeUnit.SECONDS.sleep(4);
	            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Accueil.fxml"));
	            Scene scene = new Scene(root);
	            Main.stage.setScene(scene);
	            Main.stage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
