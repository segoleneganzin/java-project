package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import piscine.Adresse;
import piscine.Employe;
import piscine.Piscine;

/**
 * Connexion a la table employe et a la table d'association travail 
 */

public class EmployeDAO extends DAO<Employe> {
	private static final String CLE_PRIMAIRE = "idEmp";
	private static final String TABLE = "employe";
	private static final String NOM = "nom";
	private static final String PRENOM = "prenom";
	private static final String FONCTION = "fonction";
	private static final String DATENAISSANCE = "dateNaissance";
	private static final String ADRESSE = "idAdresse";
	private static final String TRAVAILLEPOUR = "travail";
	private static final String ID_EMP_TRAVAILLEPOUR = "idEmp";
	private static final String ID_PISCINE_TRAVAILLEPOUR = "idPiscine";

	private static EmployeDAO instance = null;

	public static EmployeDAO getInstance() {
		if (instance == null) {
			instance = new EmployeDAO();
		}
		return instance;
	}

	private EmployeDAO() {
		super();
	}

	// CREATE
	@Override
	public boolean create(Employe employe) {
		boolean succes = true;
		try {
			Adresse adresse = employe.getAdresse();
			String requete = "INSERT INTO " + TABLE + " (" + NOM + ", " + PRENOM + ", " + FONCTION + ", "
					+ DATENAISSANCE + ", " + ADRESSE + ") VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, employe.getNom());
			pst.setString(2, employe.getPrenom());
			pst.setString(3, employe.getFonction());
			pst.setObject(4, employe.getDateNaissance());
			pst.setInt(5, adresse.getIdAdresse());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				employe.setIdEmp(rs.getInt(1));
			}
			// vérifier pour chaque piscine de la liste de piscines dans l'objet employé qu'elle existe
			for (Piscine piscine : employe.getLesPiscines()) {
				if (piscine.getIdPiscine()==-1) {
//					PiscineDAO.getInstance().create(piscine);   //pas tres securise
					System.out.println("Piscine inexistante : " + piscine);
				}
			}
			// Boucle pour sur la liste de piscines dans l'objet employé et insertion dans TRAVAILPOUR des id uniquement
			int idEmp = employe.getIdEmp();
			requete = "INSERT INTO " + TRAVAILLEPOUR + " (" + ID_EMP_TRAVAILLEPOUR + ", " + ID_PISCINE_TRAVAILLEPOUR+") VALUES (?, ?)";
			for (Piscine piscine : employe.getLesPiscines()) {
				pst = Connexion.getInstance().prepareStatement(requete);
				pst.setInt(1, idEmp);
				pst.setInt(2, piscine.getIdPiscine());
				pst.executeUpdate();					
			}
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
			// gerer les erreurs si clé etrangeres inexistantes
			if (employe.getAdresse().getIdAdresse() ==-1) {
				System.out.println("Adresse inexistante");
			}
		}
		return succes;
	}

	// READ
	@Override
	public Employe read(int id) {
		Employe employe = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.execute();			
			ResultSet rs =pst.getResultSet();
			rs.next();
			String nom = rs.getString(NOM);
			String prenom = rs.getString(PRENOM);
			String fonction = rs.getString(FONCTION);
			Date dateNaissance = rs.getDate(DATENAISSANCE);
			Adresse adresse = AdresseDAO.getInstance().read(rs.getInt(ADRESSE));
			List<Piscine> lesPiscines = new ArrayList<Piscine>();
			requete = "SELECT * FROM " + TRAVAILLEPOUR + " WHERE " + ID_EMP_TRAVAILLEPOUR + "=" + id + ";";
			rs = Connexion.executeQuery(requete);
			while (rs.next()) {
				int idP = rs.getInt(ID_PISCINE_TRAVAILLEPOUR);
				Piscine piscine = PiscineDAO.getInstance().read(idP);
				lesPiscines.add(piscine);
			}
			employe = new Employe(id, nom, prenom, fonction, dateNaissance.toLocalDate(), adresse, lesPiscines);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("employe inexistant");
		}
		return employe;
	}

	// UPDATE employe sans l'update de la table d'association
	@Override
	public boolean update(Employe obj) {
		boolean succes = true;
		String nom = obj.getNom();
		String prenom = obj.getPrenom();
		String fonction = obj.getFonction();
		LocalDate dateNaissance = obj.getDateNaissance();
		int idAdresse = obj.getAdresse().getIdAdresse();
		int id = obj.getIdEmp();
		try {
			String requete = "UPDATE " + TABLE
					+ " SET nom = ?, prenom = ?, fonction = ?, dateNaissance = ?, idAdresse = ? WHERE " + CLE_PRIMAIRE
					+ " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, nom);
			pst.setString(2, prenom);
			pst.setString(3, fonction);
			pst.setObject(4, dateNaissance);
			pst.setInt(5, idAdresse);
			pst.setInt(6, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	// DELETE
	public boolean delete(Employe obj) {
		boolean succes = true;
		try {
			int id = obj.getIdEmp();
			//supprime les lignes de la table travail si un employe est supprime
			String requete = "DELETE FROM " + TRAVAILLEPOUR +  " WHERE " + ID_EMP_TRAVAILLEPOUR +" = ?;";			
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();	

			//delete de la table admin
			requete = "DELETE FROM administrateur WHERE idAdmin = ?";
			PreparedStatement pst2 = Connexion.getInstance().prepareStatement(requete);
			pst2.setInt(1, id);
			pst2.executeUpdate();

			requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst3 = Connexion.getInstance().prepareStatement(requete);
			pst3.setInt(1, id);
			pst3.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
	
	//update de la table d'association : ajout d'une piscine travaille pour
	public boolean addPiscineTravaille(Employe emp, Piscine pis) {
		boolean succes = true;
		int idEmp = emp.getIdEmp();
		int idPis = pis.getIdPiscine();
		boolean travailleDeja = false;
		try {
			String requete = "SELECT * FROM " + TRAVAILLEPOUR + " WHERE " + ID_EMP_TRAVAILLEPOUR + "= ?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idEmp);
			pst.execute();
			ResultSet rs =pst.getResultSet();
			//on verifie si il travaille deja dans cette piscine
			while (rs.next()) {
				if (rs.getInt(ID_PISCINE_TRAVAILLEPOUR)==idPis) {
					travailleDeja = true;
				}
			}
			if(!travailleDeja) {
				requete = "INSERT INTO " + TRAVAILLEPOUR + " (" + ID_EMP_TRAVAILLEPOUR + ", " + ID_PISCINE_TRAVAILLEPOUR+") VALUES (?, ?)";
				pst = Connexion.getInstance().prepareStatement(requete);
				pst.setInt(1, idEmp);
				pst.setInt(2, idPis);
				pst.executeUpdate();	
			}
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	//update de la table d'association : suppression d'une piscine travaille pour
	public boolean deletePiscineTravaille(Employe emp, Piscine pis) {		
		boolean succes = true;
		int idEmp = emp.getIdEmp();
		int idPis = pis.getIdPiscine();
		boolean travaille = false;
		try {
			String requete = "SELECT * FROM " + TRAVAILLEPOUR + " WHERE " + ID_EMP_TRAVAILLEPOUR + "= ?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idEmp);
			pst.execute();
			ResultSet rs =pst.getResultSet();
			//on verifie si il travaille bien dans cette piscine
			while (rs.next()) {
				if (rs.getInt(ID_PISCINE_TRAVAILLEPOUR)==idPis) {
					travaille = true;
				}
			}
			if(travaille) {
				requete = "DELETE FROM " + TRAVAILLEPOUR +  " WHERE " + ID_EMP_TRAVAILLEPOUR + "= ? AND " + ID_PISCINE_TRAVAILLEPOUR + "= ?;";			
				pst = Connexion.getInstance().prepareStatement(requete);
				pst.setInt(1, idEmp);
				pst.setInt(2, idPis);
				pst.executeUpdate();	
			}
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}
}
