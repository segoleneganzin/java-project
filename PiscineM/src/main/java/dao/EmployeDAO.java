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
	//Pour la comprehension de lecture :
	private static final String ID_PISCINE = "idPiscine";


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

			// vérifier pour chaque piscine de la liste de piscines dans l'objet employé qu'il y a une ligne dans la table
			// piscine de la bd. Si la piscine n'existe pas la créer avec PiscineDAO.getInstance().create()

			for (Piscine piscine : employe.getLesPiscines()) {
				if (piscine.getIdPiscine()==-1) {
					PiscineDAO.getInstance().create(piscine);
				}
			}

			// Boucle pour sur la liste de piscines dans l'objet employé et insertion dans TRAVAILPOUR des id uniquement

			int idEmp = employe.getIdEmp();
			String requete2 = "INSERT INTO " + TRAVAILLEPOUR + " (" + ID_EMP_TRAVAILLEPOUR + ", " + ID_PISCINE_TRAVAILLEPOUR+") VALUES (?, ?)";
			for (Piscine piscine : employe.getLesPiscines()) {
				PreparedStatement pst2 = Connexion.getInstance().prepareStatement(requete2);
				pst2.setInt(1, idEmp);
				pst2.setInt(2, piscine.getIdPiscine());
				pst2.executeUpdate();					
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
			//ArrayList<Piscine> lesPiscines = EmployeDAO.getInstance().read(rs.getInt(ID_PISCINE_TRAVAILLEPOUR));
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
		}
		return employe;
	}

	// UPDATE employe avec table d'association
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
			//			System.out.println(id);


			//Update des piscines ou l'employe travail :

			for (Piscine piscine : obj.getLesPiscines()) {
				if (piscine.getIdPiscine()==-1) {
					PiscineDAO.getInstance().create(piscine);
				}
			}
			//on reccupere la liste des piscine ou l'employe travail :
			List<Integer> lesPiscinesTravail = new ArrayList<Integer>();
			requete = "SELECT * FROM " + TRAVAILLEPOUR + " WHERE " + ID_EMP_TRAVAILLEPOUR + "= ?;";
			PreparedStatement pst2 = Connexion.getInstance().prepareStatement(requete);
			pst2.setInt(1, id);
			pst2.execute();
			ResultSet rs =pst.getResultSet();
			while (rs.next()) {
				lesPiscinesTravail.add(rs.getInt(ID_PISCINE));
			}
			//			
			//on récupère la liste des piscines existantes dans la BD :
			List<Integer> lesPiscinesExistantes = new ArrayList<Integer>();
			// Boucle pour sur la liste de piscines dans l'objet employé et insertion dans TRAVAILPOUR des id uniquement
			requete = "SELECT * FROM Piscine ;";
			ResultSet rs2 = Connexion.executeQuery(requete);
			while (rs2.next()) {
				lesPiscinesExistantes.add(rs2.getInt(ID_PISCINE));
			}
			//on compare les 2 listes et on stocks les piscines existantes ou l'employe ne travaille pas deja :
			//on test l'egalite :
			List<Integer> lesPiscinesDisponibles = new ArrayList<Integer>();

			if (lesPiscinesTravail.equals(lesPiscinesExistantes)) {
				System.out.println("L'employé travail dans toutes les piscines");
			} else {
				for(int i = 0 ; i < lesPiscinesExistantes.size(); i++) {

					Integer piscineCourante = lesPiscinesExistantes.get(i);

					if (!lesPiscinesTravail.contains(piscineCourante)) {
						lesPiscinesDisponibles.add(piscineCourante);
					}
				}
			}

			//Afficher les piscines disponibles sous la forme d'un select qui propose les piscines disponibles et qui va add a une liste.
			System.out.println("Les piscines travail : " + lesPiscinesTravail);
			System.out.println("Les piscines existantes : " + lesPiscinesExistantes);
			System.out.println("Les piscines disponibles : " + lesPiscinesDisponibles);

			//delete all de travail et reinserer les bonnes lignes

			requete = "DELETE FROM " + TRAVAILLEPOUR +  " WHERE idEmp = ?;";			
				PreparedStatement pst3 = Connexion.getInstance().prepareStatement(requete);
				pst3.setInt(1, id);
				pst3.executeUpdate();		
			
				List<Piscine> lesPiscinesAjoutees = new ArrayList<Piscine>();
//				lesPiscinesAjoutees.add("recuperer la data des checkboxes");
				//TODO avec la vue
				obj.setLesPiscines(lesPiscinesAjoutees);
				String requete2 = "INSERT INTO " + TRAVAILLEPOUR + " (" + ID_EMP_TRAVAILLEPOUR + ", " + ID_PISCINE_TRAVAILLEPOUR+") VALUES (?, ?)";
				for (Piscine piscine : obj.getLesPiscines()) {
					PreparedStatement pst4 = Connexion.getInstance().prepareStatement(requete2);
					pst4.setInt(1, id);
					pst4.setInt(2, piscine.getIdPiscine());
					pst4.executeUpdate();					
				}
				
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

	}
