package piscine;

import java.time.LocalDateTime;

public class Offre {
	private int idOffre = -1; // valeur par defaut pour la gestion des erreurs
	private int validite;
	private int tarif;
	private int nbPlaces;
	private String modalite;

	public Offre(int validite, int tarif, int nbPlaces, String modalite) {
		super();
		this.validite = validite;
		this.tarif = tarif;
		this.nbPlaces = nbPlaces;
		this.modalite = modalite;
	}

	public Offre(int idOffre, int validite, int tarif, int nbPlaces, String modalite) {
		super();
		this.idOffre = idOffre;
		this.validite = validite;
		this.tarif = tarif;
		this.nbPlaces = nbPlaces;
		this.modalite = modalite;
	}

	public int getIdOffre() {
		return idOffre;
	}

	public void setIdOffre(int idOffre) {
		this.idOffre = idOffre;
	}

	public int getValidite() {
		return validite;
	}

	public void setValidite(int dureeVal) {
		this.validite = dureeVal;
	}

	public int getTarif() {
		return tarif;
	}

	public void setTarif(int tarif) {
		this.tarif = tarif;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getModalite() {
		return modalite;
	}

	public void setModalite(String modalite) {
		this.modalite = modalite;
	}

	@Override
	public String toString() {
		return "Offre [idOffre=" + idOffre + ", validite=" + validite + ", tarif=" + tarif + ", nbPlaces=" + nbPlaces
				+ ", modalite=" + modalite + "]";
	}

}