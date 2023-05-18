package dao;

public class TestDAO {

	public static void main(String[] args) {
		
		//////////////////////////////////////////////////////////TEST OFFRE (ok)
		//Creation d'une entree catalogue(offre) :		
//					Offre offre = new Offre(20,200,30,"test");
//					OffreDAO.getInstance().create(offre);

		//Read d'une offre du catalogue :
//					Offre offre = OffreDAO.getInstance().read(7);
//					System.out.println(offre);

		//Update d'une offre du catalogue :
//					offre.setValidite(36);
//					offre.setTarif(260);
//					offre.setNbPlaces(40);
//					offre.setModalite("test");
//					OffreDAO.getInstance().update(offre);

		//Delete d'une offre du catalogue :
//					OffreDAO.getInstance().delete(offre);


		//////////////////////////////////////////////////////////TEST ADRESSE	(ok)
//		Creation adresse :
//					Adresse adresse = new Adresse("15","rue Truc","Lorient", 56600);
//					AdresseDAO.getInstance().create(adresse);

//		Read d'une adresse :
//					Adresse adresse = AdresseDAO.getInstance().read(14);
//					System.out.println("read : " + adresse);

//		Update d'une adresse :
//					adresse.setNumVoie("15");
//					adresse.setRue("rue Truc");
//					adresse.setVille("Nantes");
//					AdresseDAO.getInstance().update(adresse);
//					System.out.println("update : " + adresse);

//		Delete d'une adresse :
//					AdresseDAO.getInstance().delete(adresse);


		//////////////////////////////////////////////////////////TEST COURS 	(ok)
//		Creation cours :		
//					Cours cours = new Cours("Test2", null, null, 10, EmployeDAO.getInstance().read(1), PiscineDAO.getInstance().read(2));
//					cours.setHoraireDebut(LocalDateTime.of(2023, Month.SEPTEMBER, 29, 19, 30, 40));
//					cours.setHoraireFin(LocalDateTime.of(2023, Month.SEPTEMBER, 29, 20, 30, 40));
//					CoursDAO.getInstance().create(cours);

//		read d'un cours :
//					Cours cours = CoursDAO.getInstance().read(11);
//					System.out.println(cours);

//		update d'un cours :
//					cours.setIntitule("natation synchronise");
//					cours.setHoraireDebut(LocalDateTime.of(2023, Month.OCTOBER, 29, 10, 30, 00));
//					cours.setHoraireFin(LocalDateTime.of(2023, Month.OCTOBER, 29, 11, 30, 00));
//					CoursDAO.getInstance().update(cours);
//					System.out.println(cours);

//		delete d'un cours :
//					CoursDAO.getInstance().delete(cours);


		//////////////////////////////////////////////////////////TEST PISCINE	(ok)
//		Creation piscine :
//					Piscine p1 = new Piscine("ptest", "9h00", "20h00", AdresseDAO.getInstance().read(1));
//					PiscineDAO.getInstance().create(p1);
//
//		read d'une piscine :
//					Piscine p2 = PiscineDAO.getInstance().read(8);
//					System.out.println(p2);
//
//		update d'une piscine :
//					p2.setAdresse(AdresseDAO.getInstance().read(4));
//					p2.setNom("Piscine");
//					PiscineDAO.getInstance().update(p2);
//					System.out.println(p2);
//
//		delete d'une piscine : 
//					PiscineDAO.getInstance().delete(p2);
		

		//////////////////////////////////////////////////////////TEST EMPLOYE	(ok)
//		Creation d'un employe :
//				Piscine pis = PiscineDAO.getInstance().read(2);
//				Piscine pis2 = PiscineDAO.getInstance().read(1);
//				List<Piscine> lesPiscines = new ArrayList<Piscine>();
//				lesPiscines.add(pis);
//				lesPiscines.add(pis2);
//				Employe employe = new Employe("Test4", "Roger", "Accueil", null, AdresseDAO.getInstance().read(1), lesPiscines);
//				employe.setDateNaissance(LocalDate.parse("1980-01-12"));
//				EmployeDAO.getInstance().create(employe);

//		read d'un employe :
//				Employe employe = EmployeDAO.getInstance().read(10);
//				System.out.println(employe);

//		update d'un employe :
//				employe.setNom("Test2");
//				employe.setPrenom("Bob");
//				employe.setFonction("professeur");
//				employe.setDateNaissance(LocalDate.parse("1995-12-30"));
//				employe.setAdresse(AdresseDAO.getInstance().read(1));
//				employe.setLesPiscines(lesPiscines);
//				EmployeDAO.getInstance().update(employe);		

//				System.out.println(employe);

//		delete d'un employe :
//				EmployeDAO.getInstance().delete(employe);



//////////////////////////////////////////////////////////TEST CODE	(ok)
//		Creation d'un code :
//				Code code = new Code(null, null, OffreDAO.getInstance().read(9));
//				code.setDateAchat(LocalDateTime.now());
//				CodeDAO.getInstance().create(code);

// 		read d'un code
// 				Code code = CodeDAO.getInstance().read("6Wg4gQog9x");

// 		update d'un code
// 				code.setDateAchat(LocalDateTime.of(2000, Month.AUGUST, 01, 12, 30, 00));
// 				code.setOffre(OffreDAO.getInstance().read(3));
//  			CodeDAO.getInstance().update(code);

// 		delete d'un code
// 				CodeDAO.getInstance().delete(code);

// 		System.out.println(code);
		
/////////////////////////////////////////////////////////////Test du calcul du solde d'un code (ok)
// 		read d'un code
// 				Code code = CodeDAO.getInstance().read("To9re9m5sT");
// 				Code code = CodeDAO.getInstance().read("6Wg4gQog9x");
// 				System.out.println(code.getSoldeCode());
// 				System.out.println(code);

//////////////////////////////////////////////////////////TEST ADMINISTRATEUR	(ok)
// 		Création d'un administrateur :
//				Piscine pis = PiscineDAO.getInstance().read(2);
////				Piscine pis2 = PiscineDAO.getInstance().read(1);
//				List<Piscine> lesPiscines = new ArrayList<Piscine>();
//				lesPiscines.add(pis);
//				Administrateur administrateur = new Administrateur(5, "Test4", "Roger", "Accueil", LocalDate.parse("1980-01-12"), AdresseDAO.getInstance().read(1), lesPiscines, "Test", "TestMdp");
//				AdministrateurDAO.getInstance().create(administrateur);

// 		Read d'un admin :
//				Administrateur administrateur = AdministrateurDAO.getInstance().read(5);
//				System.out.println(administrateur);

//		Update d'un admin :
//				administrateur.setIdentifiant("hello");
//				administrateur.setMdp("mdp");
//				AdministrateurDAO.getInstance().update(administrateur);

//		Delete d'un admin :
//				AdministrateurDAO.getInstance().delete(administrateur);




		//////////////////////////////////////////////////////////TEST UTILISATION
//		Creation d'une utilisation :
//				Utilisation utilisation = new Utilisation(LocalDateTime.now(), CodeDAO.getInstance().read("BHzxvDG5x3"), PiscineDAO.getInstance().read(1));
//				UtilisationDAO.getInstance().create(utilisation);
//				System.out.println(utilisation);

//		read d'une utilisation //TODO gérer le format de date 
//				Utilisation utilisation = UtilisationDAO.getInstance().read(LocalDateTime.parse("2023-05-18T16:24:46.883") , "BHzxvDG5x3");
//				System.out.println(utilisation);

//		update d'une utilisation : jamais utilisé

//		delete d'une utilisation :
//				UtilisationDAO.getInstance().delete(utilisation);
		

		Connexion.fermer();
	}

}
