package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import piscine.Main;

public class TransactionController {
	@FXML
	private void initialize() {
	    Thread thread = new Thread(() -> {
	        try {
	            Thread.sleep(3000);
	            Parent root = FXMLLoader.load(getClass().getResource("../ihm/AffichageCode.fxml"));
	            Scene scene = new Scene(root);
	            Platform.runLater(() -> {
	                Main.stage.setScene(scene);
	                Main.stage.show();
	            });
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	    thread.start();
	}
}