package application;

import java.util.List;

import dao.AdministrateurDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import piscine.Administrateur;
import piscine.Main;

public class ConnexionController {

	@FXML
	private Button retour;

	@FXML
	private Button connexion;

	@FXML
	private TextField id;

	@FXML
	private PasswordField mdp;

	@FXML
	void Retour() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/Accueil.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void AfficherPageConnexion() {
		try {
			String identifiant = id.getText();
			String motDePasse = mdp.getText();
			//            System.out.println(identifiant);
			//            System.out.println(motDePasse);
			List<Administrateur> admins = AdministrateurDAO.getInstance().readAll();

			//Intégrer une boucle for pour rechercher le bon utilisateurs
			for (Administrateur admin : admins) {
			    if (admin.getIdentifiant().equals(identifiant) && admin.getMdp().equals(motDePasse)) {
			        Parent root = FXMLLoader.load(getClass().getResource("../ihm/Admin.fxml"));
			        Scene scene = new Scene(root);
			        Main.stage.setScene(scene);
			        Main.stage.show();
			        return;
			    }
			}
			
			//TODO intégrer les message d'erreur à JAVAFX
			System.out.println("mauvais identifiant ou mot de passe");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
