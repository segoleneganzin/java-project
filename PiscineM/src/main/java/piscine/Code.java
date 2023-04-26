package piscine;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.UtilisationDAO;

public class Code {
	private String idCode = "no";	//valeur par defaut pour la gestion des erreurs
	private LocalDateTime dateAchat;
	private LocalDateTime dateEcheance;
	private Offre offre; // cle etrangere qui fera le lien avec offre

	private List<Cours> lesCours= new ArrayList<Cours>();

	public Code(LocalDateTime dateAchat, LocalDateTime dateEcheance, Offre offre, List<Cours> lesCours) {
		super();
		this.dateAchat = dateAchat;
		this.dateEcheance = dateEcheance;
		this.offre = offre;
		this.lesCours = lesCours;
	}

	public Code(String idCode, LocalDateTime dateAchat, LocalDateTime dateEcheance, Offre offre, List<Cours> lesCours) {
		super();
		this.idCode = idCode;
		this.dateAchat = dateAchat;
		this.dateEcheance = dateEcheance;
		this.offre = offre;
		this.lesCours = lesCours;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public LocalDateTime getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDateTime dateAchat) {
		this.dateAchat = dateAchat;
	}

	public LocalDateTime getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(LocalDateTime dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public List<Cours> getLesCours() {
		return lesCours;
	}

	public void setLesCours(List<Cours> lesCours) {
		this.lesCours = lesCours;
	}

	public void ajouterCours(Cours cours) {
		lesCours.add(cours);
	}

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	//consulter le solde du code
	public int getSoldeCode() {
		String idCode = this.getIdCode();
		//		System.out.println(idCode);
		int nombreUtilisation = UtilisationDAO.getInstance().getNombreUtilisation(idCode);
		//		System.out.println(nombreUtilisation);
		Offre offre = this.getOffre();
		int nbPlaces = offre.getNbPlaces();
		//		System.out.println(nbPlaces);
		int solde = nbPlaces - nombreUtilisation;
		return solde;
	}

	@Override
	public String toString() {
		return "Code [idCode = " + idCode + ", dateAchat = " + dateAchat + ", dateEcheance = " + dateEcheance
				+  ", offre = " + offre + ", entrée(s) restante(s) = " + this.getSoldeCode() + ", les cours : " + lesCours + "]";
	}

}