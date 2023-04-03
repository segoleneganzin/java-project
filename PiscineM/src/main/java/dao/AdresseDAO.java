package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import piscine.Adresse;

public class AdresseDAO extends DAO<Adresse> {
	private static final String CLE_PRIMAIRE = "idAdresse";
	private static final String TABLE = "adresse";
	private static final String NUMVOIE = "numVoie";
	private static final String RUE = "rue";
	private static final String VILLE = "ville";
	private static final String CODEPOSTAL = "codePostal";

	private static AdresseDAO instance = null;

	public static AdresseDAO getInstance() {
		if (instance == null) {
			instance = new AdresseDAO();
		}
		return instance;
	}

	private AdresseDAO() {
		super();
	}

	// CREATE
	public boolean create(Adresse adresse) {
		boolean succes = true;
		try {
			String requete = "INSERT INTO " + TABLE + " (" + NUMVOIE + ", " + RUE + ", " + VILLE + ", " + CODEPOSTAL
					+ ") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, adresse.getNumVoie());
			pst.setString(2, adresse.getRue());
			pst.setString(3, adresse.getVille());
			pst.setInt(4, adresse.getCodePostal());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				adresse.setIdAdresse(rs.getInt(1));
			}

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	// READ
	public Adresse read(int id) {
		Adresse adresse = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();
			String numVoie = rs.getString(NUMVOIE);
			String ville = rs.getString(VILLE);
			String rue = rs.getString(RUE);
			int codePostal = rs.getInt(CODEPOSTAL);
			adresse = new Adresse(id, numVoie, rue, ville, codePostal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresse;
	}

	// UPDATE
	public boolean update(Adresse obj) {
		boolean succes = true;

		String numVoie = obj.getNumVoie();
		String rue = obj.getRue();
		String ville = obj.getVille();
		int codePostal = obj.getCodePostal();
		int id = obj.getIdAdresse();

		try {
			String requete = "UPDATE " + TABLE + " SET numVoie = ?, rue = ?, ville = ?, codePostal = ? WHERE "
					+ CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, numVoie);
			pst.setString(2, rue);
			pst.setString(3, ville);
			pst.setInt(4, codePostal);
			pst.setInt(5, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	// DELETE
	public boolean delete(Adresse obj) {
		boolean succes = true;
		try {
			int id = obj.getIdAdresse();
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
//			e.printStackTrace();
			System.out.println("Attention l'adresse est utilisée dans au moins une autre table (piscine/employé)");
		}
		return succes;
	}
}