package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import piscine.Adresse;
import piscine.Piscine;


public class PiscineDAO extends DAO<Piscine> {
	private static final String CLE_PRIMAIRE = "idPiscine";
	private static final String TABLE = "piscine";
	private static final String NOM = "nom";
	private static final String HORAIREOUV = "horaireOuv";
	private static final String HORAIREFERM = "horaireFerm";
	private static final String ADRESSE = "idAdresse";



	private static PiscineDAO instance=null;

	public static PiscineDAO getInstance(){
		if (instance==null){
			instance = new PiscineDAO();
		}
		return instance;
	}

	private PiscineDAO() {
		super();
	}


	// CREATE
	public boolean create(Piscine piscine) {
		boolean succes=true;
		try {
			Adresse adresse = piscine.getAdresse();
			String requete = "INSERT INTO "+TABLE+" ("+NOM+", "+HORAIREOUV+", "+HORAIREFERM+", "+ADRESSE+") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, piscine.getNom());
			pst.setString(2, piscine.getHoraireOuv());
			pst.setString(3, piscine.getHoraireFerm());
			pst.setInt(4, adresse.getIdAdresse());
			pst.executeUpdate() ;
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				piscine.setIdPiscine(rs.getInt(1));
			}

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
			// gerer les erreurs si clé etrangeres inexistantes
			if (piscine.getAdresse().getIdAdresse() ==-1) {
				//afficher un message d'erreur
				System.out.println("Adresse inexistante");
			}
		}
		return succes;
	}

	// READ
	public Piscine read(int id) {
		Piscine piscine = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.execute();			
			ResultSet rs =pst.getResultSet();
			rs.next();
			String nomPiscine = rs.getString(NOM);
			String horaireFerm = rs.getString(HORAIREFERM);
			String horaireOuv = rs.getString(HORAIREOUV);
			Adresse adresse = AdresseDAO.getInstance().read(rs.getInt(ADRESSE));
			piscine = new Piscine(id, nomPiscine, horaireOuv, horaireFerm, adresse);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return piscine;
	}

	// UPDATE
	public boolean update(Piscine obj) {
		boolean succes=true;
		String nomPiscine =obj.getNom();
		String horaireFerm =obj.getHoraireFerm();
		String horaireOuv = obj.getHoraireOuv();
		int idAdresse = obj.getAdresse().getIdAdresse();
		int id = obj.getIdPiscine();
		try {
			String requete = "UPDATE "+TABLE+" SET nom = ?, horaireOuv = ?, horaireFerm = ?, idAdresse = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,nomPiscine) ; 
			pst.setString(2,horaireFerm) ; 
			pst.setString(3, horaireOuv) ;
			pst.setInt(4, idAdresse);
			pst.setInt(5, id) ;
			pst.executeUpdate() ;
			System.out.println(id);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}
	// DELETE
	public boolean delete(Piscine obj) {
		boolean succes=true;
		try {
			int id = obj.getIdPiscine();
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setInt(1, id) ;
			pst.executeUpdate() ;
		} catch (SQLException e) {
			succes = false;
			System.out.println("Attention la piscine est utilisée dans au moins une autre table (utilisation, cours, travail)");
		} 
		return succes;		
	}
}
