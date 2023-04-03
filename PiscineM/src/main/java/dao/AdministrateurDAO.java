package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			String requete = "INSERT INTO " + TABLE + " (" + CLE_PRIMAIRE + ", " + IDENTIFIANT + ", " + MDP
					+ ") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, administrateur.getEmploye().getIdEmp());
			pst.setString(2, administrateur.getIdentifiant());
			pst.setString(3, administrateur.getMdp());
			pst.execute();

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
			// gerer les erreurs si clé etrangeres inexistantes
			if (administrateur.getEmploye().getIdEmp() == -1) {
				// afficher un message d'erreur
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
			Employe idEmp = EmployeDAO.getInstance().read(rs.getInt(CLE_PRIMAIRE));
			String identifiant = rs.getString(IDENTIFIANT);
			String mdp = rs.getString(MDP);
			administrateur = new Administrateur(idEmp, identifiant, mdp);
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
		int id = obj.getEmploye().getIdEmp();

		try {
			String requete = "UPDATE " + TABLE + " SET identifiant = ?, mdp = ? WHERE " + CLE_PRIMAIRE + " = ?";
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
			int idAdmin = obj.getEmploye().getIdEmp();
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

}