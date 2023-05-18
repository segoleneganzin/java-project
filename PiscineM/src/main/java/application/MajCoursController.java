package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.CoursDAO;
import dao.OffreDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import piscine.Cours;
import piscine.Main;
import piscine.Offre;
import piscine.Piscine;


public class MajCoursController extends GeneralController implements Initializable{

	private ObservableList<Cours> coursData = FXCollections.observableArrayList();
	private Cours coursSelectionne;
	@FXML private TextField tarifCours;
	@FXML private TableView<Cours> tableCours;
	@FXML private TableColumn<Cours, String> intitule;
	@FXML private TableColumn<Cours, String> date;
	@FXML private TableColumn<Cours, String> heureDebut;
	@FXML private TableColumn<Cours, String> heureFin;
	@FXML private TableColumn<Cours, String> piscine;
	@FXML private Label intituleCours;
	@FXML private Label dateCours;
	@FXML private Label horaireDebut;
	@FXML private Label horaireFin;
	@FXML private Label tarifCoursAct;
	@FXML Pane modifierCours;
	@FXML Pane creerCours;
	Piscine laPiscine = GeneralController.getLaPiscine();

	public ObservableList<Cours> getCoursData() {
		return coursData;
	}

	public void initialize(URL location, ResourceBundle resources) {
		Offre uneOffre = OffreDAO.getInstance().readModalite("cours");
		tarifCoursAct.setText(String.valueOf(uneOffre.getTarif()) + "€");
		tableCours.setVisible(true);
		intitule.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIntitule()));
		date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDate()));
		heureDebut.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringHoraireDebut()));
		heureFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringHoraireFin()));
		piscine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPiscine().getNom()));
		CoursDAO coursDAO = CoursDAO.getInstance();
		List<Cours> lesCours = coursDAO.readAllCours();
		coursData.clear();
		coursData.addAll(lesCours);
		tableCours.setItems(coursData);
	}

	void modifierTarif(ActionEvent event) {
//		int tarif;
//		if (tarifCours.getText().isEmpty()) {
//			tarif= Integer.parseInt(tarifCoursAct.getText());
//		} else {
//			tarif= Integer.parseInt(tarifCours.getText());
//		}
//		Offre majOffreCours = OffreDAO.getInstance().readModalite("cours");
//		majOffreCours.setTarif(tarif);
//		OffreDAO.getInstance().update(majOffreCours);
		System.out.println("modifier le tarif");
	}
	
	@FXML
	void selectionCours() {
		// Récupérer la ligne sélectionnée
		Cours cours = tableCours.getSelectionModel().getSelectedItem();
		if (cours != null) {
			tableCours.setVisible(false);
			modifierCours.setVisible(true);
			intituleCours.setText(cours.getIntitule());
			dateCours.setText(cours.toStringDate());
			horaireDebut.setText(cours.toStringHoraireDebut());
			horaireFin.setText(cours.toStringHoraireFin());
		}
	}

	@FXML 
	void supprimerCours(ActionEvent event) {
		try {
			CoursDAO.getInstance().delete(coursSelectionne);
			tableCours.setVisible(true);
			modifierCours.setVisible(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void ajouterCours(ActionEvent event) {
		System.out.println("ajouter un cours");
//		try {
//			creerCours.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	void creerCours(ActionEvent event) {
		System.out.println("créer un cours");
	}
	
	public void fermerModification(ActionEvent event) {
		tableCours.setVisible(true);
		modifierCours.setVisible(false);
		creerCours.setVisible(false);
	}
	
	@FXML
	void retourGestionOffres(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/Accueil.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

