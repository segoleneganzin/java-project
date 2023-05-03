package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import piscine.Code;

public class AffichageCodeController extends GeneralController {
	private String code;
	@FXML private Label achatCode;

	@FXML
	private void initialize() {
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	//recupere les infos du code achete pour l'afficher
	public void setInfoCode(Code nouveauCode) {
		setCode(nouveauCode.getIdCode());
		achatCode.setText(code);
	}

}
