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

/**
 *Connexion a la table cours et a la table d'association participe afin de supprimer l'entree dans cette derniere si on supprime le cours
 */

public class CoursDAO extends DAO<Cours> {
	private static final String CLE_PRIMAIRE = "idCours";
	private static final String TABLE = "cours";
	private static final String INTITULE = "intitule";
	private static final String HORAIREDEBUT = "horaireDebut";
	private static final String HORAIREFIN = "horaireFin";
	private static final String NBPLACESINI = "nombrePlacesInitiales";
	private static final String EMPLOYE = "idEmp";
	private static final String PISCINE = "idPiscine";
	private static final String PARTICIPE = "participe";
	private static final String ID_COURS_PARTICIPE = "idCours";

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
			String requete = "INSERT INTO "+TABLE+" ("+INTITULE+", "+HORAIREDEBUT+", "+HORAIREFIN+", "+NBPLACESINI+", "+EMPLOYE +", " + PISCINE + ") VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, cours.getIntitule());
			pst.setObject(2, cours.getHoraireDebut());
			pst.setObject(3, cours.getHoraireFin());
			pst.setInt(4, cours.getNombrePlacesInitiales());
			pst.setInt(5, employe.getIdEmp());
			pst.setInt(6, piscine.getIdPiscine());
			pst.executeUpdate() ;
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				cours.setIdCours(rs.getInt(1));
			}

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
			//gerer les erreurs si clé etrangeres inexistantes
			if (cours.getEmploye().getIdEmp() == -1) {
				System.out.println("Employe inexistant");
			}
			if (cours.getPiscine().getIdPiscine() == -1) {
				System.out.println("Piscine inexistante");
			}
		}
		return succes;
	}

	// READ
	public Cours read(int id) {
		Cours cours = null;
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + CLE_PRIMAIRE + " = ? ;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, id);
			pst.execute();			
			ResultSet rs =pst.getResultSet();
			rs.next();
			String intitule = rs.getString(INTITULE);
			LocalDateTime horairedebut = rs.getTimestamp(HORAIREDEBUT).toLocalDateTime();
			LocalDateTime horairefin = rs.getTimestamp(HORAIREFIN).toLocalDateTime();
			int nbplacesini = rs.getInt(NBPLACESINI);
			Employe employe = EmployeDAO.getInstance().read(rs.getInt(EMPLOYE));
			Piscine piscine = PiscineDAO.getInstance().read(rs.getInt(PISCINE));
			cours = new Cours(id, intitule, horairedebut, horairefin, nbplacesini, employe, piscine);
		} catch (SQLException e) {
			//			e.printStackTrace();
			System.out.println("Cours inexistant");
		}
		return cours;
	}

	// UPDATE
	public boolean update(Cours obj) {
		boolean succes=true;

		String intitule =obj.getIntitule();
		LocalDateTime horairefin =obj.getHoraireFin();
		LocalDateTime horairedebut = obj.getHoraireDebut();
		int nbplacesinit = obj.getNombrePlacesInitiales();
		int idEmp = obj.getEmploye().getIdEmp();
		int idPiscine = obj.getPiscine().getIdPiscine();
		int id = obj.getIdCours();

		try {
			String requete = "UPDATE "+TABLE+" SET intitule = ?, horaireDebut = ?, horaireFin = ?, nombrePlacesInitiales = ?, idEmp = ?, idPiscine = ? WHERE "+CLE_PRIMAIRE+" = ?";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setString(1,intitule) ; 
			pst.setObject(2,horairefin) ; 
			pst.setObject(3, horairedebut) ;
			pst.setInt(4, nbplacesinit);
			pst.setInt(5, idEmp);
			pst.setInt(6, idPiscine);
			pst.setInt(7, id) ;
			pst.executeUpdate() ;
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}
	// DELETE
	public boolean delete(Cours cours) {
		boolean succes=true;
		try {
			int idCours = cours.getIdCours();
			//supprime les lignes de la table utilisation si un cours est supprime
			List<Code> lesCodesParticipes = readAllCodeParticipe(cours);
			for (Code code : lesCodesParticipes) {
				UtilisationDAO.getInstance().deleteUtilisationCode(code);
			}

			//supprime les lignes de la table participe si un cours est supprime
			String requete = "DELETE FROM " + PARTICIPE +  " WHERE " + ID_COURS_PARTICIPE +" = ?;";			
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idCours);
			pst.executeUpdate();	

			//supprime le cours
			requete = "DELETE FROM "+TABLE+" WHERE "+CLE_PRIMAIRE+" = ?";
			pst = Connexion.getInstance().prepareStatement(requete) ;
			pst.setInt(1, idCours) ;
			pst.executeUpdate() ;
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;		
	}

	//selectionner les cours posterieurs a la date du jour, avec des places restantes
	public List<Cours> readAllCoursDispo(Piscine piscine) {
		List<Cours> lesCours = new ArrayList<Cours>();
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + HORAIREDEBUT + " > GETDATE() AND " + PISCINE + " = ?;";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, piscine.getIdPiscine());
			pst.execute();	
			ResultSet rs =pst.getResultSet();
			while (rs.next()) {
				int idCours = rs.getInt(CLE_PRIMAIRE);
				Cours cours = CoursDAO.getInstance().read(idCours);
				int placesRestantes = cours.getPlacesRestantes();
				if (placesRestantes > 1) {
					lesCours.add(cours);
				}
			}} catch (SQLException e) {
				e.printStackTrace();
			}
		return lesCours;	
	}

	public List<Cours> readAllCours() {
		List<Cours> lesCours = new ArrayList<Cours>();
		try {
			String requete = "SELECT * FROM " + TABLE + " WHERE " + HORAIREDEBUT + " > GETDATE();";
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.execute();	
			ResultSet rs =pst.getResultSet();
			while (rs.next()) {
				int idCours = rs.getInt(CLE_PRIMAIRE);
				Cours cours = this.read(idCours);
				lesCours.add(cours);
			}} catch (SQLException e) {
				e.printStackTrace();
			}
		return lesCours;	
	}

	//recuperer tous les codes participants a un cours
	public List<Code> readAllCodeParticipe(Cours cours) {
		List<Code> lesCodes = new ArrayList<Code>();
		int idCours = cours.getIdCours();
		try {
			String requete = "SELECT idCode FROM " + PARTICIPE +  " WHERE " + ID_COURS_PARTICIPE +" = ?;";			
			PreparedStatement pst = Connexion.getInstance().prepareStatement(requete);
			pst.setInt(1, idCours);
			pst.execute();	
			ResultSet rs =pst.getResultSet();
			if (rs != null) {
				while (rs.next()) {
					Code code = CodeDAO.getInstance().read(rs.getString("idCode"));
					lesCodes.add(code);
				}		
			}} catch (SQLException e) {
				e.printStackTrace();
			}
		return lesCodes;
	}

	public boolean coursExiste(Cours cours) {
		return (read(cours.getIdCours())!=null);
	}

}