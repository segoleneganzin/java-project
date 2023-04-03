package piscine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Code {
	private String idCode = "no";	//valeur par defaut pour la gestion des erreurs
	private LocalDateTime dateAchat;
	private LocalDateTime dateEcheance;
	//	private int solde;
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

	//	public int getSolde() {
	//		return solde;
	//	}
	//
	//	public void setSolde(int solde) {
	//		this.solde = solde;
	//	}

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

	@Override
	public String toString() {
		return "Code [idCode = " + idCode + ", dateAchat = " + dateAchat + ", dateEcheance = " + dateEcheance
				+  ", offre = " + offre + ", les cours : " + lesCours + "]";
	}

}