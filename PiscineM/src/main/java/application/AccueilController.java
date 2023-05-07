package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import piscine.Main;


public class AccueilController extends GeneralController{

	private Parent root;

	@FXML
	void afficherReglement(ActionEvent event) {

		try {
			root = FXMLLoader.load(getClass().getResource("../ihm/Reglement.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void afficherHoraires(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/Horaires.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			HorairesController horairesController = loader.getController();
			horairesController.setHoraires();
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void achatAbo(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../ihm/Abonnement.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@FXML
	void acheterCours(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../ihm/Cours.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@FXML
	void afficherSolde(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../ihm/Solde.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@FXML
	void afficherAdmin(ActionEvent event) {

		try {
			root = FXMLLoader.load(getClass().getResource("../ihm/Connexion.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private Button simulationEntree;

	@FXML
	void simulerEntree(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("../ihm/SimulationEntree.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  

}

