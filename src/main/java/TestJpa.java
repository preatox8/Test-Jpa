import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Banque.Adresse;
import Banque.Assurancevie;
import Banque.Banque;
import Banque.Client;
import Banque.Compte;
import Banque.Livreta;
import Banque.Operation;
import Banque.Virement;
import bibliotheque.Emprunt;
import bibliotheque.Livre;

/**
 * @author ETY2
 *
 */
public class TestJpa {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestJpa.class);

	public static void main(String[] args) {
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_essai");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		/**
		 * Récupération du livre avec l'id 2 et méthode find()
		 */
		Livre l = em.find(Livre.class, 2);
		if (l != null){
			System.out.println("");
			LOGGER.info("1) ===================================> Livre : " + "ID : " + l.getId() + " -> Titre : "  + l.getTitre() + " -> Auteur : "+ l.getAuteur());
			System.out.println("");
		}
		
		
		/**
		 * 
		 * Récupération d'un livre en fonction de son titre avec une requete JPQL, ici le livre avec id = 3 avec 2 méthode
		 * 
		 */
		
		/**
		 * 1ère Méthode avec typage dans requete
		 */
		TypedQuery<Livre> query2 = em.createQuery("select l from Livre l where l.titre='Apprendre à parler aux animaux'", Livre.class);
		Livre l2 = query2.getResultList().get(0);
		System.out.println("");
		LOGGER.info("2) ===================================> Livre : " + "ID : " +  l2.getId() + " -> Titre : "  + l2.getTitre() + " -> Auteur : " +l2.getAuteur());
		System.out.println("");

		/**
		 * 2nde Méthode sans typage dans requete et avec paramètre
		 */
		Query query = em.createQuery("select l from Livre l where l.titre=:titre");
		query.setParameter("titre", "Apprendre à parler aux animaux");
		Livre l1 = (Livre) query.getResultList().get(0);
		
		System.out.println("");
		LOGGER.info("3) ===================================> Livre : " + "ID : " +  l2.getId() + " -> Titre : "  + l2.getTitre() + " -> Auteur : " + l2.getAuteur());
		System.out.println("");
		
		
		/**
		 * Requete pour extraire un emprunt avec tout ses livres
		 */
		TypedQuery<Emprunt> query3 = em.createQuery("from Emprunt e WHERE e.id='1' ", Emprunt.class);
		Emprunt e = query3.getResultList().get(0);
		List<Livre> livre1 = e.getLivre();
		
		if(e != null) {
			System.out.println("");
			LOGGER.info("4) ===================================>  Emprunt : " + e.getId());
			for(Livre li : livre1) {
				LOGGER.info("Livre : " + li.getTitre() + " -> " + li.getAuteur());
			}
			System.out.println("");
		}
		
		
		/**
		 * Requete pour extraire un emprunt avec tout ses livres
		 */
		/*TypedQuery<Clientbibliotheque> query4 = em.createQuery("from Client c WHERE c.id=2 ", Clientbibliotheque.class);
		Clientbibliotheque cli = query4.getResultList().get(0);
		List<Emprunt> emprunt1 = cli.getEmprunt();
		
		if(cli != null) {
			System.out.println("");
			LOGGER.info("5) ===================================>  Client : " + cli.getId());
			for(Emprunt emp : emprunt1) {
				LOGGER.info("Emprunt : " + emp.getId() + " -> " + emp.getDate_debut() + " / " + emp.getDate_fin());
			}
			System.out.println("");
		}*/
		
		
		
		em.close();
		entityManagerFactory.close();
		
		EntityManagerFactory entityManagerFactory1 = Persistence.createEntityManagerFactory("jpa_banque");
		EntityManager em1 = entityManagerFactory1.createEntityManager();
		EntityTransaction transaction = em1.getTransaction();

		
		try {
			Banque banque = new Banque();
			banque.setNom("Credit agricole");
			transaction = em1.getTransaction();
			transaction.begin();
			em1.persist(banque);
			transaction.commit();
		}catch(HibernateException e1) {
			LOGGER.info("erreur ajout banque : ", e1.getMessage());
			transaction.rollback();

		}
	
		
		try {
			Client client = new Client();
			client.setNom("FERRE");
			client.setPrenom("Jordan");
			client.setDatenaissance(LocalDate.of(1995, 03, 05));
			Adresse adresse = new Adresse();
			adresse.setCodepostal(85470);
			adresse.setNumero(25);
			adresse.setRue("Chemin de la chabossiere");
			adresse.setVille("BREM SUR MER");
			client.setAdresse(adresse);
			transaction = em1.getTransaction();
			transaction.begin();
			em1.persist(client);
			transaction.commit();
		}catch(HibernateException e1) {
			LOGGER.info("erreur ajout client avec adresse: ", e1.getMessage());
			transaction.rollback();

		}
		
		try {
			Operation operation = new Operation();
			operation.setDate(LocalDateTime.of(2018, 01, 16, 11, 03));
			operation.setMontant(1000D);
			operation.setMotif("ajout operation du 16-01-2018");
		
			transaction = em1.getTransaction();
			transaction.begin();
			em1.persist(operation);
			transaction.commit();
		}catch(HibernateException e1) {
			LOGGER.info("erreur ajout operation : ", e1.getMessage());
			transaction.rollback();

		}
		
		try {
			Compte compte = new Compte();
			compte.setNumero("151631165");
			compte.setSolde(666D);
			transaction = em1.getTransaction();
			transaction.begin();
			em1.persist(compte);
			transaction.commit();
		}catch(HibernateException e1) {
			LOGGER.info("erreur ajout compte : ", e1.getMessage());
			transaction.rollback();

		}
		
		try {
			Compte compte = new Compte();
			compte.setNumero("151631165");
			compte.setSolde(666D);
			transaction = em1.getTransaction();
			transaction.begin();
			em1.persist(compte);
			transaction.commit();
		}catch(HibernateException e1) {
			LOGGER.info("erreur ajout compte a une operation : ", e1.getMessage());
			transaction.rollback();
		}
		try {
			
		
		Banque banque1 = new Banque();
		banque1.setNom("rgergre");
		transaction = em1.getTransaction();
		transaction.begin();
		em1.persist(banque1);

		
		Adresse adresse1 = new Adresse();
		adresse1.setNumero(45);
		adresse1.setRue("gfregerghre");
		adresse1.setCodepostal(12584);
		adresse1.setVille("rgergr");

		
		Operation operation1 = new Operation ();
		operation1.setDate(LocalDateTime.of(2005, 03, 15, 12, 32));
		operation1.setMontant(5214D);
		operation1.setMotif("rgeethtrtr");
		em1.persist(operation1);

		Client client1 = new Client();
		client1.setNom("rgrgre");
		client1.setPrenom("thtrhtrh");
		client1.setDatenaissance( LocalDate.of(1995, 03, 05));
		client1.setAdresse(adresse1);
		client1.setBanque(banque1);
		
		em1.persist(client1);

		
		Compte compte1 = new Compte ();
		compte1.setNumero("124654");
		compte1.setSolde(4146D);		
		compte1.getOperation().add(operation1);
		compte1.getClientcompte().add(client1);
		em1.persist(compte1);

		
		Livreta liva = new Livreta(); // création d’un livret A
		liva.setTaux(1.5D);
		liva.setNumero("52");
		liva.setSolde(4566D);
		em1.persist(liva);
		
		Assurancevie assvie = new Assurancevie(); // création d’une assurance vie
		assvie.setTaux(1.2D);
		assvie.setDatefin(LocalDate.of(2019, 11, 8));
		assvie.setNumero("456789852");
		assvie.setSolde(8000D);
		em1.persist(assvie);
		
		Virement vir = new Virement();
		vir.setMontant(5000D);
		vir.setDate(LocalDateTime.of(2018, 01, 16, 20, 52));
		vir.setMotif("test ajout avec table fille virement");
		vir.setCompte(compte1);
		vir.setBeneficiaire("Anthony");
		em1.persist(vir);

		
		transaction.commit();

		}catch(HibernateException e2) {
			LOGGER.info("erreur ajout :",  e2.getMessage());
			transaction.rollback();
		}
		
		em1.close();
		entityManagerFactory1.close();
	}

}
