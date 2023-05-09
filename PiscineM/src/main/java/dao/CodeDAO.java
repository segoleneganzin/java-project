package dao;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import piscine.Code;
import piscine.Cours;
import piscine.Offre;

public class CodeDAO extends DAO<Code> {
	private static final String CLE_PRIMAIRE = "idCode";
	private static final String TABLE = "code";
	private static final String ACHAT = "dateAchat";
	private static final String ECHEANCE = "dateEcheance";
	private static final String OFFRE = "idOffre";
	private static final String PARTICIPE = "participe";
	private static final String ID_CODE_PARTICIPE = "idCode";
	private static final String ID_COURS_PARTICIPE = "idCours";

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

	public static LocalDateTime dateEcheance(Offre offre) {
		LocalDateTime dureeVal = LocalDateTime.now().plusMonths(offre.getValidite()).plusDays(1);
		return dureeVal;
	}

	// CREATE
	public boolean create(Code code) {
		boolean succes = true;
		boolean codeExiste = false;
		String codeGenere = "";
		do {
			codeGenere = generateRandomPassword();
			try {
				String checkQuery = "SELECT COUNT(*) FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ?";
				PreparedStatement checkPst = Connexion.getInstance().prepareStatement(checkQuery);
				checkPst.setString(1, codeGenere);
				ResultSet checkRs = checkPst.executeQuery();
				checkRs.next();
				int count = checkRs.getInt(1);
				if (count > 0) {
					codeExiste = true;
				} else {
					codeExiste = false;
					break;
				}
			} catch (SQLException e) {
				succes = false;
				e.printStackTrace();
			}
		} while (codeExiste);
		try {
			String insertQuery = "INSERT INTO " + TABLE + " (" + CLE_PRIMAIRE + ", " + ACHAT + ", " + ECHEANCE + ", "
					+ OFFRE + ") VALUES (?, ?, ?, ?)";
			PreparedStatement insertPst = Connexion.getInstance().prepareStatement(insertQuery);
			LocalDateTime dureeDeValidite = dateEcheance(code.getOffre());
			code.setIdCode(codeGenere);
			insertPst.setString(1, code.getIdCode());
			insertPst.setObject(2, code.getDateAchat());
			code.setDateEcheance(dureeDeValidite);
			insertPst.setObject(3, code.getDateEcheance());
			insertPst.setInt(4, code.getOffre().getIdOffre());
			insertPst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
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
			List<Cours> lesCours = new ArrayList<Cours>();
			requete = "SELECT * FROM " + PARTICIPE + " WHERE " + ID_CODE_PARTICIPE + "= ? ;";
			PreparedStatement pst2 = Connexion.getInstance().prepareStatement(requete);
			pst2.setString(1, id);
			pst2.execute();
			ResultSet rs2 = pst2.getResultSet();
			while (rs2.next()) {
				int idCours = rs2.getInt(ID_COURS_PARTICIPE);
				Cours cours = CoursDAO.getInstance().read(idCours);
				lesCours.add(cours);
			}
			code = new Code(id, dateAchat, dateEcheance, idOffre, lesCours);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return code;
	}

	// UPDATE pour la date d'echeance qui correspond a la date du cours
	public boolean update(Code obj) {
		boolean succes = true;
		LocalDateTime dateAchat = obj.getDateAchat();
		LocalDateTime dateEcheance = obj.getDateEcheance();
		int idOffre = obj.getOffre().getIdOffre();
		String idCode = obj.getIdCode();
		try {
			String requete = "UPDATE " + TABLE + " SET dateAchat = ?, dateEcheance = ?, idOffre = ? WHERE "
					+ CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, dateAchat);
			pst.setObject(2, dateEcheance);
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

	// Ajouter une participation a un cours
	public boolean ajouterParticipation(Cours cours, Code code) {
		boolean succes = true;
		try {
			String requete = "INSERT INTO " + PARTICIPE + " (" + ID_CODE_PARTICIPE + ", " + ID_COURS_PARTICIPE
					+ ") VALUES (?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, code.getIdCode());
			pst.setInt(2, cours.getIdCours());
			pst.executeUpdate();

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	public int getNombreParticipant(int cours) {
		int nombreParticipant = 0;
		try {
			String requete = "SELECT COUNT (*) FROM " + PARTICIPE + " WHERE " + ID_COURS_PARTICIPE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, cours);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			if (rs.next()) {
				nombreParticipant = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("code inexistant");
			e.printStackTrace();
		}
		return nombreParticipant;
	}

}