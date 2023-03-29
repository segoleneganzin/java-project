package application;
import javafx.event.ActionEvent;
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
    void AfficherAbo(ActionEvent event) {

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
    private Button horaires;

    @FXML
    void AfficherHoraires(ActionEvent event) {

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
    private Button reglement;

    @FXML
    void AfficherReglement(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Reglement.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private Button cours;

    @FXML
    void AfficherCours(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Cours.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private Button solde;

    @FXML
    void AfficherSolde(ActionEvent event) {

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
    private Button ticket;

    @FXML
    void AfficherTicket(ActionEvent event) {

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
    private Button admin;

    @FXML
    void AfficherAdmin(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("../ihm/Connexion.fxml"));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            Main.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

