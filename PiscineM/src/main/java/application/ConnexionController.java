package application;

import java.util.List;

import dao.AdministrateurDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import piscine.Administrateur;
import piscine.Main;

public class ConnexionController extends GeneralController {
	@FXML private Label messageErreur;

	@FXML private Button connexion;

	@FXML private TextField id;

	@FXML private PasswordField mdp;

	@FXML
	void AfficherPageConnexion() {
		try {
			String identifiant = id.getText();
			String motDePasse = mdp.getText();
			List<Administrateur> admins = AdministrateurDAO.getInstance().readAll();

			for (Administrateur admin : admins) {
				if (admin.getIdentifiant().equals(identifiant) && admin.getMdp().equals(motDePasse)) {
					Parent root = FXMLLoader.load(getClass().getResource("../ihm/Admin.fxml"));
					Scene scene = new Scene(root);
					Main.stage.setScene(scene);
					Main.stage.show();
					return;
				}
			}
			messageErreur.setText("Mauvais identifiant ou mot de passe, Veuillez réessayer !");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}