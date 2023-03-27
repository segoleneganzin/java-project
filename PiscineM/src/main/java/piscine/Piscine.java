package piscine;



public class Piscine {
	private int idPiscine = -1;	//valeur par defaut pour la gestion des erreurs
	private String nom;
	private String horaireOuv;
	private String horaireFerm;
	private Adresse adresse;
	

	public Piscine(int idPiscine, String nom, String horaireOuv, String horaireFerm, Adresse adresse) {
		super();
		this.idPiscine = idPiscine;
		this.nom = nom;
		this.horaireOuv = horaireOuv;	
		this.horaireFerm = horaireFerm;
		this.adresse = adresse;
	}
	
	//constructeur pour le create
	public Piscine(String nom, String horaireOuv, String horaireFerm, Adresse adresse) {
		super();
		this.nom = nom;
		this.horaireOuv = horaireOuv;	
		this.horaireFerm = horaireFerm;
		this.adresse = adresse;
	}
	


	public int getIdPiscine() {
		return idPiscine;
	}


	public void setIdPiscine(int idPiscine) {
		this.idPiscine = idPiscine;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getHoraireOuv() {
		return horaireOuv;
	}


	public void setHoraireOuv(String horaireOuv) {
		this.horaireOuv = horaireOuv;
	}


	public String getHoraireFerm() {
		return horaireFerm;
	}


	public void setHoraireFerm(String horaireFerm) {
		this.horaireFerm = horaireFerm;
	}


	public Adresse getAdresse() {
		return adresse;
	}


	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	@Override
	public String toString() {
		return "Piscine [idPiscine=" + idPiscine + ", nom=" + nom + ", horaireOuv=" + horaireOuv + ", horaireFerm="
				+ horaireFerm + ", adresse=" + adresse + "]";
	}


		

}
