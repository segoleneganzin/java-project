package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import piscine.Code;
import piscine.Cours;
import piscine.Employe;
import piscine.Piscine;

public class CoursDAO extends DAO<Cours> {
	private static final String CLE_PRIMAIRE = "idCours";
	private static final String TABLE = "cours";
	
	private static final String INTITULE = "intitule";
	private static final String HORAIREDEBUT = "horaireDebut";
	private static final String HORAIREFIN = "horaireFin";
	private static final String NBPLACESINI = "nombrePlacesInitiales";
	private static final String PLACESREST = "placesRestantes";
	private static final String EMPLOYE = "idEmp";
	private static final String PISCINE = "idPiscine";
	private static final String PARTICIPE = "participe";
	private static final String ID_CODE_PROPOSE = "idCode";
	private static final String ID_COURS_PROPOSE = "idCours";
	
	private static CoursDAO instance=null;

	public static CoursDAO getInstance(){
		if (instance==null){
			instance = new CoursDAO();
		}
		return instance;
	}

	private CoursDAO() {
		super();
	}


	// CREATE
	public boolean create(Cours cours) {

		boolean succes=true;
		try {
			Employe employe = cours.getEmploye();
			Piscine piscine = cours.getPiscine();
			
			String requete = "INSERT INTO "+TABLE+" ("+INTITULE+", "+HORAIREDEBUT+", "+HORAIREFIN+", "+NBPLACESINI+", "+PLACESREST+", "+EMPLOYE +", " + PISCINE + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, cours.getIntitule());
			pst.setObject(2, cours.getHoraireDebut());
			pst.setObject(3, cours.getHoraireFin());
			pst.setInt(4, cours.getNombrePlacesInitiales());
			pst.setInt(5, cours.getPlacesRestantes());
			pst.setInt(6, employe.getIdEmp());
			pst.setInt(7, piscine.getIdPiscine());
			pst.executeUpdate() ;
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				cours.setIdCours(rs.getInt(1));
			}

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
			// TODO gerer les erreurs si clé etrangeres inexistantes
			if (cours.getEmploye().getIdEmp() == -1) {
				//EmployeDAO.getInstance().create(employe);
				//afficher un message d'erreur
			}
			if (cours.getPiscine().getIdPiscine() == -1) {
				//EmployeDAO.getInstance().create(employe);
				//afficher un message d'erreur
			}
		}
		return succes;
	}

	// READ
	public Cours read(int id) {
		Cours cours = null;
		try {
			String requete = "SELECT * FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+"="+id+";";
			ResultSet rs = Connexion.executeQuery(requete);
			rs.next();
			String intitule = rs.getString(INTITULE);
			LocalDateTime horairedebut = rs.getTimestamp(HORAIREDEBUT).toLocalDateTime();
			LocalDateTime horairefin = rs.getTimestamp(HORAIREFIN).toLocalDateTime();
			int nbplacesini = rs.getInt(NBPLACESINI);
			int placesrest = rs.getInt(PLACESREST);
			Employe employe = EmployeDAO.getInstance().read(rs.getInt(EMPLOYE));
			Piscine piscine = PiscineDAO.getInstance().read(rs.getInt(PISCINE));
			List<Code> lesCodes = new ArrayList<Code>();
			requete = "SELECT * FROM " + PARTICIPE + " WHERE " + ID_COURS_PROPOSE + "=" + id + ";";
			rs = Connexion.executeQuery(requete);
			while (rs.next()) {
				String idCode = rs.getString(ID_CODE_PROPOSE);
				Code code = CodeDAO.getInstance().read(idCode);
				lesCodes.add(code);
			}
			cours = new Cours(id, intitule, horairedebut, horairefin, nbplacesini, placesrest, employe, piscine, lesCodes);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cours;
	}

	// TODO UPDATE cours avec table d'association
	public boolean update(Cours obj) {
		boolean succes=true;

		String intitule =obj.getIntitule();
		LocalDateTime horairefin =obj.getHoraireFin();
		LocalDateTime horairedebut = obj.getHoraireDebut();
		int nbplacesinit = obj.getNombrePlacesInitiales();
		int placesrest = obj.getPlacesRestantes();
		int idEmp = obj.getEmploye().getIdEmp();
		int idPiscine = obj.getPiscine().getIdPiscine();
		int id = obj.getIdCours();

		try {
			String requete = "UPDATE "+TABLE+" SET intitule = ?, horaireDebut = ?, horaireFin = ?, nombrePlacesInitiales = ?, placesRestantes = ?, idEmp = ?, idPiscine = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,intitule) ; 
			pst.setObject(2,horairefin) ; 
			pst.setObject(3, horairedebut) ;
			pst.setInt(4, nbplacesinit);
			pst.setInt(5, placesrest);
			pst.setInt(6, idEmp);
			pst.setInt(7, idPiscine);
			pst.setInt(8, id) ;
			pst.executeUpdate() ;
			System.out.println(id);
			
		//Update des codes participants au cours :
		
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}
	// DELETE
	public boolean delete(Cours obj) {
		boolean succes=true;
		try {
			int id = obj.getIdCours();
			String requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setInt(1, id) ;
			pst.executeUpdate() ;
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;		
	}
}
