package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import piscine.Piscine;

public class HorairesController extends GeneralController {

	@FXML
	private Label horaireOuv;
	@FXML
	private Label horaireFerm;

	// recupere l'objet piscine de la classe mere et affiche les horaires presents
	// dans la bd:
	@FXML
	public void setHoraires() {
		Piscine laPiscine = GeneralController.getLaPiscine();
		horaireOuv.setText(String.valueOf(laPiscine.getHoraireOuv()));
		horaireFerm.setText(String.valueOf(laPiscine.getHoraireFerm()));
	}

}