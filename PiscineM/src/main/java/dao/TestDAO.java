package dao;

import java.time.LocalDateTime;
import java.time.Month;

import piscine.Adresse;
import piscine.Code;
import piscine.Offre;

public class TestDAO {

	public static void main(String[] args) {
		////////////////////////////////////////////////////////// TEST OFFRE
		// Creation d'une entree catalogue(offre) :
//		Offre offre = new Offre(20, 200, 30, "solo");
//		OffreDAO.getInstance().create(offre);

		// Read d'une offre du catalogue :
//		Offre offre = OffreDAO.getInstance().read(2);
//		System.out.println(offre);

		// Update d'une offre du catalogue :
//		offre.setValidite(36);
//		offre.setTarif(260);
//		offre.setNbPlaces(40);
//		offre.setModalite("duo");
//		OffreDAO.getInstance().update(offre);

		// Delete d'une offre du catalogue :
//		OffreDAO.getInstance().delete(offre);

//////////////////////////////////////////////////////////TEST ADRESSE
		// Creation adresse :
//		Adresse adresse = new Adresse("15", "rue Truc", "Lorient", 56600);
//		AdresseDAO.getInstance().create(adresse);

		// Read d'une adresse :
//		Adresse adresse = AdresseDAO.getInstance().read(1);
//		System.out.println("read : " + adresse);

		// Update d'une adresse :
//		adresse.setNumVoie("15");
//		adresse.setRue("rue Truc");
//		AdresseDAO.getInstance().update(adresse);
//		System.out.println("update : " + adresse);

		// Delete d'une adresse :
//		AdresseDAO.getInstance().delete(adresse);

//////////////////////////////////////////////////////////TEST COURS
		// Creation cours :
//		Cours cours = new Cours("Aqua", null, null, 10, 10, EmployeDAO.getInstance().read(1),
//		PiscineDAO.getInstance().read(2));
//		cours.setHoraireDebut(LocalDateTime.of(2023, Month.SEPTEMBER, 29, 19, 30, 40));
//		cours.setHoraireFin(LocalDateTime.of(2023, Month.SEPTEMBER, 29, 20, 30, 40));
//		CoursDAO.getInstance().create(cours);

		// read d'un cours :
//		Cours cours = CoursDAO.getInstance().read(4);
//		System.out.println(cours);

		// update d'un cours :
//		cours.setIntitule("natation");
//		CoursDAO.getInstance().update(cours);
//		System.out.println(cours);

		// delete d'un cours :
//		CoursDAO.getInstance().delete(cours);

//////////////////////////////////////////////////////////TEST PISCINE
		// Creation piscine :
//		Piscine p1 = new Piscine("p10", "9h00", "20h00", AdresseDAO.getInstance().read(1));
//		PiscineDAO.getInstance().create(p1);

		// read d'une piscine :
//		Piscine p2 = PiscineDAO.getInstance().read(5);
//		System.out.println(p2);

		// update d'une piscine :
//		p2.setAdresse(AdresseDAO.getInstance().read(4));
//		p2.setNom("Piscine");
//		PiscineDAO.getInstance().update(p2);
//		System.out.println(p2);

		// delete d'une piscine :
//		PiscineDAO.getInstance().delete(p2);

//////////////////////////////////////////////////////////TEST EMPLOYE
		// Creation d'un employe :
//		Piscine pis = PiscineDAO.getInstance().read(1);
//		List<Piscine> lesPiscines = new ArrayList<Piscine>();
//		lesPiscines.add(pis);
//		Employe employe = new Employe("Test", "Michel", "Admin", null, AdresseDAO.getInstance().read(1), lesPiscines);
//		employe.setDateNaissance(LocalDate.parse("1980-01-12"));
//		EmployeDAO.getInstance().create(employe);

		// read d'un employe :
//		Employe employe = EmployeDAO.getInstance().read(4);
//		System.out.println(employe);

		// update d'un employe :
//		employe.setNom("Test2");
//		employe.setPrenom("Bernard");
//		employe.setFonction("Work");
//		employe.setDateNaissance(LocalDate.parse("1990-12-30"));
//		employe.setAdresse(AdresseDAO.getInstance().read(1));
//		EmployeDAO.getInstance().update(employe);

//		System.out.println(employe);

		// delete d'un employe :
//		EmployeDAO.getInstance().delete(employe);

//////////////////////////////////////////////////////////TEST CODE
//		Creation d'un code :
//		Code code = new Code(null, null, OffreDAO.getInstance().read(2));
//		code.setDateAchat(LocalDateTime.now());
//				//automatisation de la date d'echeance du code :
//				//TODO automatiser en fonction de offre.dureeValidite
//		code.setDateEcheance(LocalDate.now().plusMonths(10).plusDays(1));
//		CodeDAO.getInstance().create(code);

		// read d'un code
		Code code = CodeDAO.getInstance().read("OgoZfOy1Bb");

		// update d'un code
		code.setDateEcheance(LocalDateTime.of(2099, Month.JANUARY, 01, 13, 30, 00));
		code.setOffre(OffreDAO.getInstance().read(3));
		CodeDAO.getInstance().update(code);

		// delete d'un code
//		CodeDAO.getInstance().delete(code);

//		System.out.println(code);

//////////////////////////////////////////////////////////TEST UTILISATION
		// Creation d'une utilisation :
//		Utilisation utilisation = new Utilisation(LocalDateTime.now(), CodeDAO.getInstance().read("To9re9m5sT"),
//		PiscineDAO.getInstance().read(1));
//		UtilisationDAO.getInstance().create(utilisation);
//		System.out.println(utilisation);

		// read d'une utilisation
//		Utilisation utilisation = UtilisationDAO.getInstance().read(LocalDateTime.parse("2023-03-29T14:30:48.163"),
//				"To9re9m5sT");
//		System.out.println(utilisation);

		// update d'une utilisation : jamais utilisé

		// delete d'une utilisation :
//		UtilisationDAO.getInstance().delete(utilisation);

//////////////////////////////////////////////////////////TEST ADMINISTRATEUR
		// Création d'un administrateur :
//		Administrateur administrateur = new Administrateur(EmployeDAO.getInstance().read(4), "Test", "TestMdp");
//		AdministrateurDAO.getInstance().create(administrateur);

		// Read d'un admin :
//		Administrateur administrateur = AdministrateurDAO.getInstance().read(3);
//		System.out.println(administrateur);

		// Update d'un admin :
//		administrateur.setEmploye(EmployeDAO.getInstance().read(3)); // utile ?
//		administrateur.setIdentifiant("hello");
//		administrateur.setMdp("mdp");
//		AdministrateurDAO.getInstance().update(administrateur);

		// // Delete d'un admin :
//		AdministrateurDAO.getInstance().delete(administrateur);

//		System.out.println(administrateur);

		Connexion.fermer();
	}

}
