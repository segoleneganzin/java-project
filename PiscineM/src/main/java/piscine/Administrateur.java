package piscine;

public class Administrateur {
	private Employe employe;
	private String identifiant;
	private String mdp;

	public Administrateur(Employe employe, String identifiant, String mdp) {
		super();
		this.employe = employe;
		this.identifiant = identifiant;
		this.mdp = mdp;
	}

	public Employe getEmploye() {
		return employe;
	}
	
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
		return "Administrateur [employe= " + employe  + ", mdp= " + mdp + ", " + " identifiant= " + identifiant + "]";
	}

}