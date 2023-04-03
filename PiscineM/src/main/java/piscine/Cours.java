package piscine;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Cours {
	private int idCours;
	private String intitule;
	private LocalDateTime horaireDebut;
	private LocalDateTime horaireFin;
	private int nombrePlacesInitiales;
	private int placesRestantes;
	private Employe employe;	//cle etrangere
	private Piscine piscine;  //cle etrangere
//	private List<Code> lesCodes = new ArrayList<Code>();	//lien tables d'association "participe"

	//constructeur sans idCours ni liste de codes :
	public Cours(String intitule, LocalDateTime horaireDebut, LocalDateTime horaireFin, int nombrePlacesInitiales, int placesRestantes, Employe employe, Piscine piscine) {
		super();
		this.horaireFin = horaireFin;
		this.horaireDebut = horaireDebut;
		this.intitule = intitule;
		this.nombrePlacesInitiales = nombrePlacesInitiales;
		this.placesRestantes = placesRestantes;
		this.employe = employe;
		this.piscine = piscine;
	}
		
	//constructeur sans codes 
	public Cours(int idCours, String intitule, LocalDateTime horaireDebut, LocalDateTime horaireFin, int nombrePlacesInitiales, int placesRestantes, Employe employe, Piscine piscine) {
		super();
		this.idCours = idCours;
		this.horaireFin = horaireFin;
		this.horaireDebut = horaireDebut;
		this.intitule = intitule;
		this.nombrePlacesInitiales = nombrePlacesInitiales;
		this.placesRestantes = placesRestantes;
		this.employe = employe;
		this.piscine = piscine;
	}
	
	////Constructeur avec id et liste de cours, pour l'update 
//	public Cours(int idCours, String intitule, LocalDateTime horaireDebut, LocalDateTime horaireFin, int nombrePlacesInitiales, int placesRestantes, Employe employe, Piscine piscine, List<Code> lesCodes) {
//		super();
//		this.idCours = idCours;
//		this.horaireFin = horaireFin;
//		this.horaireDebut = horaireDebut;
//		this.intitule = intitule;
//		this.nombrePlacesInitiales = nombrePlacesInitiales;
//		this.placesRestantes = placesRestantes;
//		this.employe = employe;
//		this.piscine = piscine;
//		this.setLesCodes(lesCodes);
//	}
	


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


	public int getPlacesRestantes() {
		return placesRestantes;
	}


	public void setPlacesRestantes(int placesRestantes) {
		this.placesRestantes = placesRestantes;
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

//	public List<Code> getLesCodes() {
//		return lesCodes;
//	}
//
//	public void setLesCodes(List<Code> lesCodes) {
//		this.lesCodes = lesCodes;
//	}

	@Override
	public String toString() {
		return "Cours [idCours=" + idCours + ", intitule=" + intitule + ", horaireDebut=" + horaireDebut
				+ ", horaireFin=" + horaireFin + ", nombrePlacesInitiales=" + nombrePlacesInitiales
				+ ", placesRestantes=" + placesRestantes + ", employe=" + employe + "]";
	}

	
	
	


	
}