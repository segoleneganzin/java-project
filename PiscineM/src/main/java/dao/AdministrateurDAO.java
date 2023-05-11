package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import piscine.Administrateur;
import piscine.Employe;

public class AdministrateurDAO extends DAO<Administrateur> {
	private static final String CLE_PRIMAIRE = "idAdmin";
	private static final String TABLE = "administrateur";
	private static final String IDENTIFIANT = "identifiant";
	private static final String MDP = "mdp";

	private static AdministrateurDAO instance = null;
	
	public static AdministrateurDAO getInstance() {
		if (instance == null) {
			instance = new AdministrateurDAO();
		}
		return instance;
	}
	
	private AdministrateurDAO() {
		super();
	}
	
	// CREATE 
	public boolean create(Administrateur administrateur) {
		boolean succes = true;
		try {
			String requete = "INSERT INTO " + TABLE + " (" + CLE_PRIMAIRE + ", " + IDENTIFIANT + ", " + MDP + ") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, administrateur.getIdEmp());
			pst.setString(2, administrateur.getIdentifiant());
			pst.setString(3, administrateur.getMdp());
			pst.execute();
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
			// gerer les erreurs si clé etrangeres inexistantes
			System.out.println("Employé inexistant");
			if (administrateur.getIdEmp() ==-1) {
				//afficher un message d'erreur
				System.out.println("Employé inexistant");
			}
		}
		return succes;
	}
	
	// READ
	public Administrateur read(int id) {
		Administrateur administrateur = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();
			Employe emp = EmployeDAO.getInstance().read(rs.getInt(CLE_PRIMAIRE));
			administrateur = new Administrateur(emp.getIdEmp(),emp.getNom() , emp.getPrenom(),emp.getFonction(), emp.getDateNaissance(), emp.getAdresse(), emp.getLesPiscines(),  rs.getString(IDENTIFIANT), rs.getString(MDP));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administrateur;
	}
	
	// UPDATE
	public boolean update(Administrateur obj) {
		boolean succes = true;
		
		String identifiant = obj.getIdentifiant();
		String mdp = obj.getMdp();
		int id = obj.getIdEmp();
		
		try {
			String requete = "UPDATE " + TABLE 
			+ " SET identifiant = ?, mdp = ? WHERE "
			+ CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, identifiant);
			pst.setString(2, mdp);
			pst.setInt(3, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	
	// DELETE
	public boolean delete(Administrateur obj) {
		boolean succes = true;
		try {
			int idAdmin = obj.getIdEmp();
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idAdmin);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	
	public List<Administrateur> readAll() {
	    List<Administrateur> admins = new ArrayList<>();
	    try {
//	        Connection conn = (Connection) AdministrateurDAO.getInstance();
	        Statement stmt = Connexion.getInstance().createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLE );
	        while (rs.next()) {
	        	// récupérer l'employe rs.getInt(Employe)
	        	Employe emp = EmployeDAO.getInstance().read(rs.getInt(CLE_PRIMAIRE));
	        	// tout envoyer dans le constructeur de Admin
	            Administrateur admin = new Administrateur(emp.getIdEmp(),emp.getNom() , emp.getPrenom(),emp.getFonction(), emp.getDateNaissance(), emp.getAdresse(), emp.getLesPiscines(),  rs.getString(IDENTIFIANT), rs.getString(MDP));
	            admins.add(admin);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return admins;
	}
	
}