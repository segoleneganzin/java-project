package application;

import java.net.URL;
import java.util.ResourceBundle;

import dao.CodeDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import piscine.Code;
import piscine.Main;

public class SoldeController implements Initializable{
	
	@FXML private Pane codeInfosContainer;
	@FXML private Button retour;
	@FXML private TextField code;
	@FXML private Label messageErreur;
	@FXML private Label dateAchat;
	@FXML private Label dateEcheance;
	@FXML private Label modaliteOffre;
	@FXML private Label solde;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//methode implementee
	}
	
	@FXML
	public void Retour() {
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
	public void afficherCode() {
		try {
			String idCode = code.getText();
			//rechercher le code dans la bd
			Code unCode = CodeDAO.getInstance().read(idCode);
			if (unCode != null) {
				codeInfosContainer.setVisible(true);
				dateAchat.setText(String.valueOf(unCode.getDateAchat()));
				dateEcheance.setText(String.valueOf(unCode.getDateEcheance()));
				modaliteOffre.setText(unCode.getOffre().getModalite());
				solde.setText(String.valueOf(unCode.getSoldeCode()));
			} else {
				messageErreur.setText("Code inexistant, Veuillez réessayer !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	




}
