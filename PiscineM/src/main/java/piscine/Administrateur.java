package piscine;

import java.time.LocalDate;
import java.util.List;

public class Administrateur extends Employe {
	private String identifiant;
	private String mdp;

	public Administrateur(int idEmp, String nom, String prenom, String fonction, LocalDate dateNaissance,
			Adresse adresse, List<Piscine> lesPiscines, String identifiant, String mdp) {
		super(idEmp, mdp, mdp, mdp, dateNaissance, adresse, lesPiscines);
		this.identifiant = identifiant;
		this.mdp = mdp;
	}

//	public Employe getEmploye() {
//		return employe;
//	}
	
	//Jamais utilisé 
//	public void setEmploye(Employe employe) {
//		this.employe = employe;
//	}
	
	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "Administrateur [" + super.toString()  + ", mdp= " + mdp + ", " + " identifiant= " + identifiant + "]";
	}

}