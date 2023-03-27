package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import piscine.Main;

public class AccueilController {

    @FXML
    private Button abo;
    
    @FXML
    private Button connexion;

    @FXML
    private Button solde;
    
    @FXML
    private Button cours;
    
    @FXML
    void AfficherAbo() {
    	
    	try {
            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Abonnement.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	@FXML
    private Button ticket;

    @FXML
    void AfficherTicket() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Ticket.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
    
    @FXML
    void AfficherConnexion() {
    	try {
            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Connexion.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    private Button horaires;

    @FXML
    void AfficherHoraires() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Horaires.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    void AfficherSolde() {
    	try {
            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Solde.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void AfficherCours() {
    	try {
            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Cours.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    
}
