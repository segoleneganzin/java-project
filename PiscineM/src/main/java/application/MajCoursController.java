package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import dao.CoursDAO;
import dao.EmployeDAO;
import dao.OffreDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import piscine.Cours;
import piscine.Employe;
import piscine.Main;
import piscine.Offre;
import piscine.Piscine;


public class MajCoursController extends GeneralController implements Initializable{

	private ObservableList<Cours> coursData = FXCollections.observableArrayList();
	private Cours coursSelectionne;
	@FXML private Text titrePage;
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
	@FXML private Label messageErreur;
	@FXML private TextField intituleNvCours;
	@FXML private DatePicker dateDebNvCours;
	@FXML private TextField hDebNvCours;
	@FXML private TextField minDebNvCours;
	@FXML private DatePicker dateFinNvCours;
	@FXML private TextField hFinNvCours;
	@FXML private TextField minFinNvCours;
	@FXML private TextField nbPlacesInitialesNvCours;
	@FXML private ChoiceBox<String> employesExistants;
	@FXML Pane modifierCours;
	@FXML Pane paneTarifCours;
	@FXML Pane creerCours;
	Piscine laPiscine = GeneralController.getLaPiscine();

	public ObservableList<Cours> getCoursData() {
		return coursData;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableCours.setVisible(true);
		creerCours.setVisible(false);
		afficherCours();
		initializeChoiceBox();
	}

	private void afficherCours() {
		Offre uneOffre = OffreDAO.getInstance().readModalite("cours");
		tarifCoursAct.setText(String.valueOf(uneOffre.getTarif()) + "€");
		intitule.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIntitule()));
		date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDate()));
		heureDebut.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringHoraireDebut()));
		heureFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringHoraireFin()));
		piscine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPiscine().getNom()));
		List<Cours> lesCours = CoursDAO.getInstance().readAllCours();
		coursData.clear();
		coursData.addAll(lesCours);
		tableCours.setItems(coursData);
	}

	public void modifierTarif() {
		int tarif;
		if (tarifCours.getText().isEmpty()) {
			tarif= Integer.parseInt(tarifCoursAct.getText());
		} else {
			tarif= Integer.parseInt(tarifCours.getText());
		}
		Offre majOffreCours = OffreDAO.getInstance().readModalite("cours");
		majOffreCours.setTarif(tarif);
		OffreDAO.getInstance().update(majOffreCours);
		afficherCours();
	}

	public void selectionCours() {
		titrePage.setVisible(false);
		// Récupérer la ligne sélectionnée
		coursSelectionne = tableCours.getSelectionModel().getSelectedItem();
		if (coursSelectionne != null) {
			tableCours.setVisible(false);
			modifierCours.setVisible(true);
			paneTarifCours.setVisible(false);
			intituleCours.setText(coursSelectionne.getIntitule());
			dateCours.setText(coursSelectionne.toStringDate());
			horaireDebut.setText(coursSelectionne.toStringHoraireDebut());
			horaireFin.setText(coursSelectionne.toStringHoraireFin());
		}
	}

	public void supprimerCours() {
		try {
			CoursDAO.getInstance().delete(coursSelectionne);
			afficherCours();
			tableCours.setVisible(true);
			modifierCours.setVisible(false);
			paneTarifCours.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initializeChoiceBox() {
		List<Employe> listeEmployes = EmployeDAO.getInstance().readAllProf(laPiscine);
		for (Employe employe : listeEmployes) {
			employesExistants.getItems().add(employe.getIdEmp() + " " + employe.getNom());
		}
	}

	public void ajouterCours() {
		titrePage.setVisible(false);
		creerCours.setVisible(true);

	}

	public void creerCours() {
		Cours cours = new Cours("", null, null, 0, null, null);
		String intitule = intituleNvCours.getText();
		LocalDate date = dateDebNvCours.getValue();
		String hDebText = hDebNvCours.getText();
		String minDebText = minDebNvCours.getText();
		String hFinText = hFinNvCours.getText();
		String minFinText = minFinNvCours.getText();
		String nbPlacesInit = nbPlacesInitialesNvCours.getText();
		String nomEmp = (String) employesExistants.getValue();
		messageErreur.setVisible(false);

		if (!intitule.isEmpty() && date != null && !hDebText.isEmpty() && !minDebText.isEmpty() && !hFinText.isEmpty() && !minFinText.isEmpty() && !nbPlacesInit.equals("") && !nomEmp.equals(null)) {
			try {
				int heureDeb = Integer.parseInt(hDebText);
				int minuteDeb = Integer.parseInt(minDebText);
				int heureFin = Integer.parseInt(hFinText);
				int minuteFin = Integer.parseInt(minFinText);

				LocalDateTime dateHeureMinDeb = LocalDateTime.of(date, LocalTime.of(heureDeb, minuteDeb));
				LocalDateTime dateHeureMinFin = LocalDateTime.of(date, LocalTime.of(heureFin, minuteFin));

				String[] idNomProf = nomEmp.split(" ");
				Integer idProf = Integer.parseInt(idNomProf[0]);
				Employe prof = EmployeDAO.getInstance().read(idProf);

				cours.setIntitule(intitule);
				cours.setHoraireDebut(dateHeureMinDeb);
				cours.setHoraireFin(dateHeureMinFin);
				cours.setNombrePlacesInitiales(Integer.parseInt(nbPlacesInit));
				cours.setEmploye(prof);
				cours.setPiscine(laPiscine);
				CoursDAO.getInstance().create(cours);
				dateDebNvCours.setValue(LocalDate.now());;
				intituleNvCours.clear();
				hDebNvCours.clear();
				minDebNvCours.clear();
				hFinNvCours.clear();
				minFinNvCours.clear();
				nbPlacesInitialesNvCours.clear();
				creerCours.setVisible(false);
				afficherCours();
			} catch (NumberFormatException e) {
				messageErreur.setVisible(true);
			}
		} else {
			messageErreur.setVisible(true);
		}
	}

	public void fermerPane() {
		titrePage.setVisible(true);
		tableCours.setVisible(true);
		modifierCours.setVisible(false);
		creerCours.setVisible(false);
		paneTarifCours.setVisible(true);
		afficherCours();
		messageErreur.setVisible(false);
	}

	public void retourGestionOffres() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../ihm/Admin.fxml"));
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

