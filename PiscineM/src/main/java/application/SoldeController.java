package application;

import java.net.URL;
import java.util.ResourceBundle;

import dao.CodeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import piscine.Code;
import piscine.Main;

public class SoldeController implements Initializable{

	@FXML private TableView<Code> tableCode;
	@FXML private TableColumn<Code, String> dateAchat;
	@FXML private TableColumn<Code, String> dateEcheance;
	public ObservableList<Code> dataCode = FXCollections.observableArrayList();
	
	@FXML
	private Button retour;
	@FXML
	private TextField code;
	@FXML
	private Label messageErreur;
	@FXML
	private Label solde;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
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
				dataCode.add(unCode);
				solde.setText(String.valueOf(unCode.getSoldeCode()));
			} else {
				messageErreur.setText("Code inexistant, Veuillez réessayer !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		dateAchat.setCellValueFactory(new PropertyValueFactory<Code, String>("dateAchat"));
		dateEcheance.setCellValueFactory(new PropertyValueFactory<Code, String>("dateEcheance"));
//		solde.setCellValueFactory(new PropertyValueFactory<Code, Integer>("solde"));
		tableCode.setItems(dataCode);
		
		

	}
	




}
