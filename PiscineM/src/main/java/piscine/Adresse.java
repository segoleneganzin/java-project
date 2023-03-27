package piscine;

public class Adresse {

	private int idAdresse = -1; //valeur par defaut pour la gestion des erreurs
	private String numVoie;
	private String rue;
	private String ville;
	private int codePostal;

	
	public Adresse(String numVoie, String rue, String ville, int codePostal) {
		super();
		this.numVoie = numVoie;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
	}

	public Adresse(int idAdresse, String numVoie, String rue, String ville, int codePostal) {
		super();
		this.idAdresse = idAdresse;
		this.numVoie = numVoie;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;

	}

	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public String getNumVoie() {
		return numVoie;
	}

	public void setNumVoie(String numVoie) {
		this.numVoie = numVoie;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return "Adresse [idAdresse=" + idAdresse + ", numVoie=" + numVoie + ", rue=" + rue + ", ville=" + ville
				+ ", codePostal=" + codePostal + "]";
	}
	
}