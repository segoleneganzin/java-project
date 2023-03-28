package piscine;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.CodeDAO;
import dao.Connexion;
import dao.OffreDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage stage;
	@Override
	public void start(Stage racine) throws Exception {
		// Nœud racine.
		this.stage = racine;
		Parent root = FXMLLoader.load(getClass().getResource("../ihm/Accueil.fxml"));
		// Configuration de la scène.
		Scene scene = new Scene(root,546,439);
		// Configuration de la fenêtre.
		racine.setScene(scene);
		racine.setTitle("Bienvenue aux Piscines Vannetaises");
		racine.show();
	}

	public static String genererCode(int len) {
		// Liste des caractères à utiliser pour le mdp (0-9, a-z, A-Z)
		// final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		final String chars = "0123456789";

		SecureRandom random = new SecureRandom();
		// Constructeur d'une chaîne de caractère
		StringBuilder sb = new StringBuilder();

		// Chaque itération de la boucle choisit aléatoirement un caractère dans la liste plus haut et l'ajoute à l'instance `StringBuilder`
		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Connexion.getInstance();

		launch(args);

		//Creation d'une entree offre :
//		Offre offre = new Offre(10,100,20,"solo");
//		OffreDAO.getInstance().create(offre);

		//Read d'une offre :
		//				Offre offre = OffreDAO.getInstance().read(1);

		//Update d'une offre d'une offre :
		//		offre.setValidite(36);
		//		offre.setTarif(260);
		//		offre.setNbPlaces(40);
		//		OffreDAO.getInstance().update(offre);

		//Delete d'une offre d'une offre :
		//				OffreDAO.getInstance().delete(offre);

//		System.out.println(offre);


		//Creation adresse :
		//		Adresse adresse = new Adresse("15","rue Toto","Lorient", 56600);
		//		AdresseDAO.getInstance().create(adresse);

		//Read d'une adresse :
		//		Adresse adresse = AdresseDAO.getInstance().read(1);

		//Update d'une adresse :
		//		adresse.setNumVoie("15");
		//		adresse.setRue("rue Truc");
		//		AdresseDAO.getInstance().update(adresse);

		//Delete d'une adresse :
		//		AdresseDAO.getInstance().delete(adresse);

		//		System.out.println(adresse);


		//Creation cours :
		//		Cours cours = new Cours("natation", null, null, 10, 10);
		//		cours.setHoraireDebut(LocalDateTime.of(2023, Month.JULY, 29, 19, 30, 40));
		//		cours.setHoraireFin(LocalDateTime.of(2023, Month.JULY, 29, 19, 30, 40));
		//		CoursDAO.getInstance().create(cours);

		//read d'un cours :
		//		Cours cours = CoursDAO.getInstance().read(1);

		//update d'un cours :
		//		cours.setIntitule("aquaponey");
		//		CoursDAO.getInstance().update(cours);

		//delete d'un cours :
		//		CoursDAO.getInstance().delete(cours);

		//		System.out.println(cours);


		//Creation piscine :
		//		Piscine p1 = new Piscine("p5", "9:00:00", "20:00:00", AdresseDAO.getInstance().read(1));
		//		PiscineDAO.getInstance().create(p1);

		//read d'une piscine :
		//		Piscine p1 = PiscineDAO.getInstance().read(1);

		//update d'une piscine :
		//		p1.setAdresse(AdresseDAO.getInstance().read(2));
		//		p1.setNom("Kercado");
		//		PiscineDAO.getInstance().update(p1);

		//		System.out.println(p1);


		//Creation d'un employe :
		//		Employe employe = new Employe("Test", "Michel", "Admin", null, AdresseDAO.getInstance().read(1));
		//		employe.setDateNaissance(LocalDate.parse("1980-01-12"));
		//		EmployeDAO.getInstance().create(employe);

		//read d'un employe :
		//				Employe employe = EmployeDAO.getInstance().read(2);

		//update d'un employe :
		//				employe.setNom("Test2");
		//				employe.setPrenom("Bernard");
		//				employe.setFonction("Work");
		//				employe.setDateNaissance(LocalDate.parse("1990-12-30"));
		//				employe.setAdresse(AdresseDAO.getInstance().read(1));
		//				EmployeDAO.getInstance().update(employe);

		//delete d'un employe :
		//		EmployeDAO.getInstance().delete(employe);

		//				System.out.println(employe);


		//		Creation d'un code :
				int len = 10;
				Code code = new Code(null, null, null, OffreDAO.getInstance().read(3));
				code.setIdCode(genererCode(len));
				code.setDateAchat(LocalDateTime.now());
				code.setDateEcheance(LocalDate.now().plusMonths(10).plusDays(1));
				CodeDAO.getInstance().create(code);

		//read d'un code
		//		Code code = CodeDAO.getInstance().read("Bu2ZsqgMIb");

		//update d'un  code
		//		code.setOffre(OffreDAO.getInstance().read(2));
		//		CodeDAO.getInstance().update(code);

		//delete d'un code
		//		CodeDAO.getInstance().delete(code);

				System.out.println(code);


		// Création d'un administrateur :
		//		Administrateur administrateur = new Administrateur(EmployeDAO.getInstance().read(1), "Test", "TestMdp");
		//		AdministrateurDAO.getInstance().create(administrateur);

		// Read d'un admin :
		//		Administrateur administrateur = AdministrateurDAO.getInstance().read(1);

		//		Update d'un admin :
		//		administrateur.setEmploye(EmployeDAO.getInstance().read(2));
		//		administrateur.setIdentifiant("TestID");
		//		administrateur.setMdp("TestMdP2");
		//		AdministrateurDAO.getInstance().update(administrateur);

		//		// Delete d'un admin :
		//		AdministrateurDAO.getInstance().delete(administrateur);

		//		System.out.println(administrateur);

		Connexion.fermer();
	}
}