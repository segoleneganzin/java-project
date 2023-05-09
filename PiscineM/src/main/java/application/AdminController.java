package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class AdminController extends GeneralController {
	@FXML
	private Text loginName;
	@FXML
	private Button connexion;

	@FXML
	private void initialize() {
		// TODO Recuperer le nom
		loginName.setText("admin");
	}

}