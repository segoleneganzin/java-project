package piscine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employe {

	private int idEmp = -1;	//valeur par defaut pour la gestion des erreurs
	private String nom;
	private String prenom;
	private String fonction;
	private LocalDate dateNaissance;
	private Adresse adresse; //cle etrangere
	private List<Piscine> lesPiscines = new ArrayList<Piscine>(); //lien tables d'association "travail"


	public Employe(int idEmp, String nom, String prenom, String fonction, LocalDate dateNaissance, Adresse adresse, List<Piscine> lesPiscines) {
		super();
		this.idEmp = idEmp;
		this.fonction = fonction;
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.setLesPiscines(lesPiscines);
	}

	// Constructeur utilisé pour le create
	public Employe(String nom, String prenom, String fonction, LocalDate dateNaissance, Adresse adresse, List<Piscine> lesPiscines) {
		super();
		this.fonction = fonction;
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.setLesPiscines(lesPiscines);
	}

	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Piscine> getLesPiscines() {
		return lesPiscines;
	}

	public void setLesPiscines(List<Piscine> lesPiscines) {
		this.lesPiscines = lesPiscines;
	}

	@Override
	public String toString() {
		return "Employe [idEmp=" + idEmp + ", nom=" + nom + ", prenom=" + prenom + ", fonction=" + fonction
				+ ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", lesPiscines=" + lesPiscines + "]";
	}
	
	

	

}