package application;

import java.time.LocalDateTime;
import java.util.List;

import dao.CodeDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import piscine.Code;
import piscine.Cours;

public class SoldeController extends GeneralController{

	@FXML private Pane codeInfosAboContainer;
	@FXML private Pane codeInfosCoursContainer;
	@FXML private TextField code;
	@FXML private Label messageErreur;
	@FXML private Label dateAchat;
	@FXML private Label dateEcheance;
	@FXML private Label modaliteOffre;
	@FXML private Label solde;
	@FXML private Label offreCours;
	@FXML private Label dateAchatCours;
	private ObservableList<Cours> coursData = FXCollections.observableArrayList();
	@FXML private TableView<Cours> tableCours;
	@FXML private TableColumn<Cours, String> intitule;
	@FXML private TableColumn<Cours, String> date;
	@FXML private TableColumn<Cours, String> heureDebut;
	@FXML private TableColumn<Cours, String> heureFin;
	@FXML private TableColumn<Cours, String> piscine;

	public ObservableList<Cours> getCoursData() {
		return coursData;
	}
	
	@FXML
	public void afficherCode() {
		try {
			String idCode = code.getText();
			//rechercher le code dans la bd
			Code unCode = CodeDAO.getInstance().read(idCode);
			if (unCode != null) {
				LocalDateTime echeance = unCode.getDateEcheance();
				//test si la date d'echeance est posterieure a "aujourd'hui" :
				if (echeance.isAfter(LocalDateTime.now())) {
					if (unCode.getOffre().getModalite().equals("solo") || unCode.getOffre().getModalite().equals("duo")) {
						codeInfosCoursContainer.setVisible(false);
						messageErreur.setVisible(false);
						codeInfosAboContainer.setVisible(true);
						dateAchat.setText(String.valueOf(unCode.toStringDateAchat()));
						dateEcheance.setText(String.valueOf(unCode.toStringDateEcheance()));
						modaliteOffre.setText(unCode.getOffre().getModalite());
						solde.setText(String.valueOf(unCode.getSoldeCode()));
					} 
					else if(unCode.getOffre().getModalite().equals("cours")) {
						codeInfosCoursContainer.setVisible(true);
						codeInfosAboContainer.setVisible(false);
						offreCours.setText(String.valueOf(unCode.getOffre().getModalite()));
						dateAchatCours.setText(String.valueOf(unCode.toStringDateAchat()));
						//recuperation des cours (pour le moment toujours un seul mais le tableau permet de faire évoluer les offres) :
						List<Cours> lesCours = unCode.getLesCours();
						intitule.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIntitule()));
						date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringDate()));
						heureDebut.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringHoraireDebut()));
						heureFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().toStringHoraireFin()));
						piscine.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPiscine().getNom()));
						coursData.clear();
						coursData.addAll(lesCours);
						tableCours.setItems(coursData);
					}
				}
				else {
					codeInfosCoursContainer.setVisible(false);
					codeInfosAboContainer.setVisible(false);
					messageErreur.setVisible(true);
					messageErreur.setText("Code expiré le "+echeance);
				}
			} else {
				codeInfosCoursContainer.setVisible(false);
				codeInfosAboContainer.setVisible(false);
				messageErreur.setVisible(true);
				messageErreur.setText("Code inexistant, Veuillez réessayer !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
