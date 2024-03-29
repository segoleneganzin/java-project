package piscine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.CodeDAO;


public class Cours {
	private int idCours = -1;
	private String intitule;
	private LocalDateTime horaireDebut;
	private LocalDateTime horaireFin;
	private int nombrePlacesInitiales;
	private Employe employe;	//cle etrangere
	private Piscine piscine;  //cle etrangere

	//constructeur sans idCours :
	public Cours(String intitule, LocalDateTime horaireDebut, LocalDateTime horaireFin, int nombrePlacesInitiales, Employe employe, Piscine piscine) {
		super();
		this.horaireFin = horaireFin;
		this.horaireDebut = horaireDebut;
		this.intitule = intitule;
		this.nombrePlacesInitiales = nombrePlacesInitiales;
		this.employe = employe;
		this.piscine = piscine;
	}
		
	//constructeur avec idCours : 
	public Cours(int idCours, String intitule, LocalDateTime horaireDebut, LocalDateTime horaireFin, int nombrePlacesInitiales, Employe employe, Piscine piscine) {
		super();
		this.idCours = idCours;
		this.horaireFin = horaireFin;
		this.horaireDebut = horaireDebut;
		this.intitule = intitule;
		this.nombrePlacesInitiales = nombrePlacesInitiales;
		this.employe = employe;
		this.piscine = piscine;
	}
	
	public int getIdCours() {
		return idCours;
	}


	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}


	public String getIntitule() {
		return intitule;
	}


	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public LocalDateTime getHoraireDebut() {
		return horaireDebut;
	}


	public void setHoraireDebut(LocalDateTime horaireDebut) {
		this.horaireDebut = horaireDebut;
	}


	public LocalDateTime getHoraireFin() {
		return horaireFin;
	}


	public void setHoraireFin(LocalDateTime horaireFin) {
		this.horaireFin = horaireFin;
	}


	public int getNombrePlacesInitiales() {
		return nombrePlacesInitiales;
	}


	public void setNombrePlacesInitiales(int nombrePlacesInitiales) {
		this.nombrePlacesInitiales = nombrePlacesInitiales;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Piscine getPiscine() {
		return piscine;
	}

	public void setPiscine(Piscine piscine) {
		this.piscine = piscine;
	}
	
	
	public int getPlacesRestantes() {
		int idCours = this.getIdCours();
		int nombreParticipant = CodeDAO.getInstance().getNombreParticipant(idCours);
		int nbPlacesInitiales = this.getNombrePlacesInitiales();
		int nbPlacesRestantes = nbPlacesInitiales - nombreParticipant;
		return nbPlacesRestantes;
	}
	
	//pour afficher les dates "joliment" :
	public String toStringDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
	    return horaireDebut.format(formatter);
	}
	
	public String toStringHoraireDebut() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	    return horaireDebut.format(formatter);
	}
	
	public String toStringHoraireFin() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	    return horaireFin.format(formatter);
	}
	


	@Override
	public String toString() {
		return "Cours [idCours=" + idCours + ", intitule=" + intitule + ", date : " + toStringDate() +", horaireDebut=" + toStringHoraireDebut()
				+ ", horaireFin=" + toStringHoraireFin() + ", nombrePlacesInitiales=" + nombrePlacesInitiales
				+ ", placesRestantes=" + this.getPlacesRestantes() + ", employe=" + employe + "]";
	}

	
	
	


	
}