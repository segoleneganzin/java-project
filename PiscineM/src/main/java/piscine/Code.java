package piscine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Code {
	private String idCode = "no";	//valeur par defaut pour la gestion des erreurs
	private LocalDateTime dateAchat;
	private LocalDate dateEcheance;
//	private int solde;
	//	private Piscine piscine;  cle etrangere qui fera le lien avec la piscine
	private Offre offre; // cle etrangere qui fera le lien avec catalogue

	private List<Utilisation> lesUtilisations; //TODO ajouter les utilisation du code dans cette liste
	
	//	public Code(LocalDateTime dateAchat, LocalDateTime dateEcheance, int solde, Catalogue catalogue) {
	//		super();
	//		this.dateAchat = dateAchat;
	//		this.dateEcheance = dateEcheance;
	//		this.solde = solde;
	////		this.piscine = piscine;
	//		this.catalogue = catalogue;
	//	}

	public Code(String idCode, LocalDateTime dateAchat, LocalDate dateEcheance, Offre offre) {
		super();
		this.idCode = idCode;
		this.dateAchat = dateAchat;
		this.dateEcheance = dateEcheance;
		//		this.piscine = piscine;
		this.offre = offre;
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

	public LocalDate getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(LocalDate dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

//	public int getSolde() {
//		return solde;
//	}
//
//	public void setSolde(int solde) {
//		this.solde = solde;
//	}

	//	public Piscine getPiscine() {
	//		return piscine;
	//	}
	//
	//	public void setPiscine(Piscine piscine) {
	//		this.piscine = piscine;
	//	}

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	@Override
	public String toString() {
		return "Code [idCode = " + idCode + ", dateAchat = " + dateAchat + ", dateEcheance = " + dateEcheance
				+  ", offre = " + offre + "]";
	}

}