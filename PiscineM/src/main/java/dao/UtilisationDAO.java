package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import piscine.Code;
import piscine.Piscine;
import piscine.Utilisation;

public class UtilisationDAO  extends DAO<Utilisation> {
	private static final String CLE_PRIMAIRE = "dateUtilisation, idCode";
	private static final String TABLE = "utilisation";
	private static final String DATE_UTILISATION = "dateUtilisation";
	private static final String CODE = "idCode";
	private static final String PISCINE = "idPiscine";

	private static UtilisationDAO instance=null;

	public static UtilisationDAO getInstance(){
		if (instance==null){
			instance = new UtilisationDAO();
		}
		return instance;
	}

	private UtilisationDAO() {
		super();
	}
	
	@Override
	public boolean create(Utilisation utilisation) {
		boolean succes = true;
		try {
			String requete = "INSERT INTO " + TABLE + " (" + CLE_PRIMAIRE + ", " + PISCINE +  ") VALUES (?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, utilisation.getDateUtilisation());
			pst.setString(2, utilisation.getCode().getIdCode());
			pst.setInt(3, utilisation.getPiscine().getIdPiscine());
			pst.execute();

		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
			// vérifier que le code existe sinon message d'erreur
			if (utilisation.getCode().getIdCode() == "no") {
				System.out.println("code inexistant");
			}
			// vérifier que la piscine existe sinon message d'erreur
			if (utilisation.getPiscine().getIdPiscine() == -1) {
				System.out.println("piscine inexistante");
			}
			
			
		}
		return succes;
	}

	public Utilisation read(LocalDateTime dateUtilisation, String idCode) {
		Utilisation utilisation = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + DATE_UTILISATION + " = ? AND " + CODE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, dateUtilisation);
			pst.setString(2, idCode);
			pst.execute();
			ResultSet rs =pst.getResultSet();
			rs.next();
			dateUtilisation = rs.getTimestamp(DATE_UTILISATION).toLocalDateTime();
			Piscine idPiscine = PiscineDAO.getInstance().read(rs.getInt(PISCINE));
			Code code = CodeDAO.getInstance().read(rs.getString(CODE));
			utilisation = new Utilisation(dateUtilisation, code, idPiscine);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Utilisation inexistante");
		}
		return utilisation;
	}

	@Override
	//Cette fonction ne sera jamais utilisée
	public boolean update(Utilisation obj) {
		boolean succes = true;
		LocalDateTime dateUtilisation = obj.getDateUtilisation();
		String idCode = obj.getCode().getIdCode();
		int idPiscine = obj.getPiscine().getIdPiscine();
		
		try {
			String requete = "UPDATE " + TABLE
					+ " SET dateUtilisation = ?, idCode = ?, idPiscine = ? WHERE "
					+ CLE_PRIMAIRE + " = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, dateUtilisation);
			pst.setObject(2, idCode);
			pst.setInt(3, idPiscine);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	@Override
	public boolean delete(Utilisation obj) {
		boolean succes = true;
		try {
			LocalDateTime dateUtilisation = obj.getDateUtilisation();
			String idCode = obj.getCode().getIdCode();
			String requete = "DELETE FROM " + TABLE + " WHERE " + DATE_UTILISATION + " = ? AND " + CODE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setObject(1, dateUtilisation);
			pst.setString(2, idCode);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
	}

	
	//calcul le nombre d'utilisation pour ensuite pouvoir calculer le solde
	public int getNombreUtilisation(String code) {
		int nombreUtilisation = 0;
		try {
			String requete = "SELECT COUNT (*) FROM " + TABLE + " WHERE " + CODE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, code);
			pst.execute();
			ResultSet rs =pst.getResultSet();
			if (rs.next()) {
	            nombreUtilisation = rs.getInt(1);
	        }
		} catch (SQLException e) {
			System.out.println("code inexistant");
			e.printStackTrace();
		}
		return nombreUtilisation;
	}
	
	public boolean deleteUtilisationCode(Code code) {
		boolean succes = true;
		try {
			String idCode = code.getIdCode();
			String requete = "DELETE FROM " + TABLE + " WHERE " + CODE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setString(1, idCode);
			pst.executeUpdate();
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		}
		return succes;
		
	}

	@Override
	public Utilisation read(int id) {
		throw new IllegalArgumentException();
	}

	
}