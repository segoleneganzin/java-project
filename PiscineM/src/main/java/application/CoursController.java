package application;

import java.util.List;

import dao.CoursDAO;
import dao.OffreDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import piscine.Cours;
import piscine.Main;
import piscine.Offre;
import piscine.Piscine;


public class CoursController extends GeneralController{

	private ObservableList<Cours> coursData = FXCollections.observableArrayList();
	private Cours coursSelectionne;
	private Parent root;
	@FXML private TableView<Cours> tableCours;
    @FXML private TableColumn<Cours, String> intitule;
	@FXML private TableColumn<Cours, String> date;
	@FXML private TableColumn<Cours, String> heureDebut;
	@FXML private TableColumn<Cours, String> heureFin;
	@FXML private Button afficherCours;
	@FXML private Label intituleCours;
	@FXML private Label dateCours;
	@FXML private Label horaireDebut;
	@FXML private Label horaireFin;
	@FXML Pane reserverCours;
	Piscine laPiscine = GeneralController.getLaPiscine();

	public Cours getCoursSelectionne() {
		return coursSelectionne;
	}

	public void setCoursSelectionne(Cours coursSelectionne) {
		this.coursSelectionne = coursSelectionne;
	}
	
	public ObservableList<Cours> getCoursData() {
		return coursData;
	}
	
	public void afficherCours(ActionEvent event) {
			   tableCours.setVisible(true);
			   afficherCours.setVisible(false);
			   intitule.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIntitule()));
			   date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDate()));
			   heureDebut.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringHoraireDebut()));
			   heureFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringHoraireFin()));
			   CoursDAO coursDAO = CoursDAO.getInstance();
			   List<Cours> lesCours = coursDAO.readAllCoursDispo(laPiscine);
			   coursData.clear();
			   coursData.addAll(lesCours);
			   tableCours.setItems(coursData);
	}
	
	public void fermerReservation(ActionEvent event) {
		reserverCours.setVisible(false);
	}
	
	@FXML
	void selectionCours() {
		// Récupérer la ligne sélectionnée
	    setCoursSelectionne(tableCours.getSelectionModel().getSelectedItem());
	    System.out.println(getCoursSelectionne());
	    Cours cours = getCoursSelectionne();
	    if (cours != null) {
	    	reserverCours.setVisible(true);
	    	intituleCours.setText(cours.getIntitule());
	    	dateCours.setText(cours.toStringDate());
	    	horaireDebut.setText(cours.toStringHoraireDebut());
	    	horaireFin.setText(cours.toStringHoraireFin());
	    }
	}
	
	@FXML 
	void acheterCours() {
		try {
			Offre uneOffre = OffreDAO.getInstance().readModalite("cours");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ihm/Paiement.fxml"));
			root = loader.load();
			PaiementController paiementController = loader.getController();
			paiementController.setInfo(uneOffre);
			paiementController.setInfoCours(getCoursSelectionne());
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

