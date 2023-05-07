package application;

import dao.PiscineDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import piscine.Main;
import piscine.Piscine;

public class ChoixPiscineController {
	private Parent root;
	@FXML private Button vanocea;
	@FXML private Button kercado;
	private Piscine unePiscine;
	@FXML
	void selectionnerPiscine(ActionEvent event) {
		try {
			//si offre solo :
			if(event.getSource() == vanocea) {
				unePiscine = PiscineDAO.getInstance().read(1);
			} 
			//si offre duo :
			if (event.getSource() == kercado) {
				unePiscine = PiscineDAO.getInstance().read(2);
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/Accueil.fxml"));
			root = loader.load();
			//envoi de l'objet piscine dans la classe mere :
			GeneralController generalController = loader.getController();
			generalController.setInfoPiscine(unePiscine);
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
