package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import piscine.Adresse;
import piscine.Code;
import piscine.Offre;

public class CodeDAO extends DAO<Code> {
	private static final String CLE_PRIMAIRE = "idCode";
	private static final String TABLE = "code";
	private static final String ACHAT = "dateAchat";
	private static final String ECHEANCE = "dateEcheance";
//	private static final String SOLDE = "solde";
	//	private static final String PISCINE = "idPiscine";
	private static final String OFFRE = "idOffre";

	private static CodeDAO instance = null;

	public static CodeDAO getInstance() {
		if (instance == null) {
			instance = new CodeDAO();
		}
		return instance;
	}

	private CodeDAO() {
		super();
	}

	// CREATE
	public boolean create(Code code) {
		boolean succes = true;
		try {
			String requete = "INSERT INTO " + TABLE + " (" + CLE_PRIMAIRE + ", " + ACHAT + ", " + ECHEANCE + ", " + OFFRE + ") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, code.getIdCode());
			pst.setObject(2, code.getDateAchat());
			pst.setObject(3, code.getDateEcheance());
//			pst.setInt(4, code.getSolde());
			//			pst.setInt(5, code.getPiscine().getIdPiscine());
			pst.setInt(4, code.getOffre().getIdOffre());
			pst.execute();
			//			ResultSet rs = pst.getGeneratedKeys();
			//			if (rs.next()) {
			//				code.setIdCode(rs.getString(1));
			//			}

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
			// TODO gerer les erreurs si clé etrangeres inexistantes
			if (code.getOffre().getIdOffre() ==-1) {
				//OffreDAO.getInstance().create(offre);
				//afficher un message d'erreur
			}
		}
		return succes;
	}

	// READ
	//Obligatoire en int avant d'override en string
	public Code read(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Code read(String id) {
		Code code = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, id);
			pst.execute();
			//			ResultSet rs = Connexion.executeQuery(requete);
			ResultSet rs =pst.getResultSet();
			rs.next();
			LocalDateTime dateAchat = rs.getTimestamp(ACHAT).toLocalDateTime();
			LocalDateTime dateEcheance = rs.getTimestamp(ECHEANCE).toLocalDateTime();
//			int solde = rs.getInt(SOLDE);
			//			Piscine idPiscine = PiscineDAO.getInstance().read(rs.getInt(PISCINE));
			Offre idOffre = OffreDAO.getInstance().read(rs.getInt(OFFRE));
			code = new Code(id, dateAchat, dateEcheance.toLocalDate(), idOffre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;
	}

	// UPDATE
	public boolean update(Code obj) {
		boolean succes = true;

		LocalDateTime dateAchat = obj.getDateAchat();
		LocalDate dateEcheance = obj.getDateEcheance();
//		int solde = obj.getSolde();
		//		int idPiscine = obj.getPiscine().getIdPiscine();
		int idOffre = obj.getOffre().getIdOffre();
		String idCode = obj.getIdCode();

		try {
			String requete = "UPDATE " + TABLE
					+ " SET dateAchat = ?, dateEcheance = ?, idCatalogue = ? WHERE "
					+ CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, dateAchat);
			pst.setObject(2, dateEcheance);
//			pst.setInt(3, solde);
			//			pst.setInt(4, idPiscine);
			pst.setInt(4, idOffre);
			pst.setString(5, idCode);
			pst.executeUpdate();
			//			System.out.println(idCode);
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	// DELETE
	public boolean delete(Code obj) {
		boolean succes = true;
		try {
			String idCode = obj.getIdCode();
			String requete = "DELETE FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, idCode);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}




}