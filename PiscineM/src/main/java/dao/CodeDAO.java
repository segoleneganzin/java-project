package dao;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import piscine.Code;
import piscine.Offre;

public class CodeDAO extends DAO<Code> {
	private static final String CLE_PRIMAIRE = "idCode";
	private static final String TABLE = "code";
	private static final String ACHAT = "dateAchat";
	private static final String ECHEANCE = "dateEcheance";
	// private static final String SOLDE = "solde";
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

	// Méthode pour générer un mot de passe alphanumérique aléatoire d'une longueur
	// 10
	public static String generateRandomPassword() {
		// Gamme ASCII – alphanumérique (0-9, a-z, A-Z)
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		// chaque itération de la boucle choisit aléatoirement un caractère parmi les
		// données
		// Plage ASCII et l'ajoute à l'instance `StringBuilder`
		for (int i = 0; i < 10; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}
		return sb.toString();
	}

	// CREATE
	public boolean create(Code code) {
		boolean succes = true;
		String generatedCode = generateRandomPassword();
		try {
			String requete = "INSERT INTO " + TABLE + " (" + CLE_PRIMAIRE + ", " + ACHAT + ", " + ECHEANCE + ", "
					+ OFFRE + ") VALUES (?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			code.setIdCode(generatedCode);
			pst.setString(1, code.getIdCode());
			pst.setObject(2, code.getDateAchat());
			pst.setObject(3, code.getDateEcheance());
			// pst.setInt(4, code.getSolde());
			pst.setInt(4, code.getOffre().getIdOffre());
			pst.executeUpdate();

			// ResultSet rs = code.setIdCode(rs.generateRandomPassword(len));
//			ResultSet rs = pst.getGeneratedKeys();
//			if (rs.next()) {
//				code.setIdCode(generateRandomPassword(len));
//			}

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
			// gerer les erreurs si clé etrangeres inexistantes
			if (code.getOffre().getIdOffre() == -1) {
				System.out.println("Offre inexistante");
			}
		}
		return succes;
	}

	// READ
	// Obligatoire en int avant d'override en string
	public Code read(int id) {
		return null;
	}

	public Code read(String id) {
		Code code = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();
			LocalDateTime dateAchat = rs.getTimestamp(ACHAT).toLocalDateTime();
			LocalDateTime dateEcheance = rs.getTimestamp(ECHEANCE).toLocalDateTime();
			// int solde = rs.getInt(SOLDE);
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
		// int solde = obj.getSolde();
		int idOffre = obj.getOffre().getIdOffre();
		String idCode = obj.getIdCode();

		try {
			String requete = "UPDATE " + TABLE + " SET dateAchat = ?, dateEcheance = ?, idOffre = ? WHERE "
					+ CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, dateAchat);
			pst.setObject(2, dateEcheance);
			// pst.setInt(3, solde);
			pst.setInt(3, idOffre);
			pst.setString(4, idCode);
			pst.executeUpdate();
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