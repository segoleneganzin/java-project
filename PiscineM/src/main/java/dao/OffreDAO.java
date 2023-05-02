//les offres sont gerees par l'admin
//il peut :
// -les modifier
// -les supprimer
// - en ajouter
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import piscine.Offre;

public class OffreDAO extends DAO<Offre> {
	private static final String CLE_PRIMAIRE = "idOffre";
	private static final String TABLE = "offre";
	private static final String VALIDITE = "validite"; // mois
	private static final String TARIF = "tarif";
	private static final String NBPLACE = "nbPlaces";
	private static final String MODALITE = "modalite"; // solo/duo/cours

	private static OffreDAO instance = null;

	public static OffreDAO getInstance() {
		if (instance == null) {
			instance = new OffreDAO();
		}
		return instance;
	}

	private OffreDAO() {
		super();
	}

	// CREATE
	public boolean create(Offre offre) {

		boolean succes = true;
		try {
			String requete = "INSERT INTO " + TABLE + " (" + VALIDITE + ", " + TARIF + ", " + NBPLACE + ", " + MODALITE
					+ ") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setObject(1, offre.getValidite());
			pst.setInt(2, offre.getTarif());
			pst.setInt(3, offre.getNbPlaces());
			pst.setString(4, offre.getModalite());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				offre.setIdOffre(rs.getInt(1));
			}

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	// READ
	public Offre read(int id) {
		Offre offre = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();
			int validite = rs.getInt(VALIDITE);
			int nbplace = rs.getInt(NBPLACE);
			int tarif = rs.getInt(TARIF);
			String modalite = rs.getString(MODALITE);
			offre = new Offre(id, validite, tarif, nbplace, modalite);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offre;
	}

	// UPDATE
	public boolean update(Offre obj) {
		boolean succes = true;

		int id = obj.getIdOffre();
		int validite = obj.getValidite();
		int tarif = obj.getTarif();
		int nbPlace = obj.getNbPlaces();
		String modalite = obj.getModalite();

		try {
			String requete = "UPDATE " + TABLE + " SET validite = ?, tarif = ?, nbPlaces = ?, modalite = ? WHERE "
					+ CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);

			pst.setInt(1, validite);
			pst.setInt(2, tarif);
			pst.setInt(3, nbPlace);
			pst.setString(4, modalite);
			pst.setInt(5, id);

			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	// DELETE
	public boolean delete(Offre obj) {
		boolean succes = true;
		try {
			int id = obj.getIdOffre();
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
//			e.printStackTrace();
			System.out.println("Attention l'offre est utilisée dans une autre table (code)");
		}
		return succes;
	}

	public Offre readModalite(String modalite) {
		Offre offre = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + MODALITE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, modalite);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();
			int idOffre = rs.getInt(CLE_PRIMAIRE);
			int validite = rs.getInt(VALIDITE);
			int nbplace = rs.getInt(NBPLACE);
			int tarif = rs.getInt(TARIF);
			offre = new Offre(idOffre, validite, tarif, nbplace, modalite);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offre;
	}

}